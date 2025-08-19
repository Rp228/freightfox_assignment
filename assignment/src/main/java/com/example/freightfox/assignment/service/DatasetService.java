package com.example.freightfox.assignment.service;

import com.example.freightfox.assignment.model.DatasetRecord;
import com.example.freightfox.assignment.repository.DatasetRecordRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DatasetService {

    @Autowired
    private DatasetRecordRepository datasetRecordRepository;

    @Autowired
    private ObjectMapper objectMapper;

    // Insert record into DB
    public Long insertRecord(String datasetName, String json) {
        try {
            DatasetRecord record = new DatasetRecord();
            record.setDatasetName(datasetName);
            record.setJsonData(json);
            DatasetRecord saved = datasetRecordRepository.save(record);
            return saved.getId();  // return the generated ID
        } catch (Exception e) {
            throw new RuntimeException("Invalid JSON provided", e);
        }
    }


    // Query records with optional groupBy and sorting
    public Object query(String datasetName, String groupBy, String sortBy, String order) {
        List<DatasetRecord> records = datasetRecordRepository.findByDatasetName(datasetName);

        // Convert DB JSON to JsonNode
        List<JsonNode> jsonNodes = records.stream()
                .map(r -> {
                    try {
                        return objectMapper.readTree(r.getJsonData());
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // If groupBy is provided → group and return
        if (groupBy != null && !groupBy.isEmpty()) {
            return jsonNodes.stream()
                    .collect(Collectors.groupingBy(node -> {
                        JsonNode valueNode = node.get(groupBy);
                        return (valueNode != null && !valueNode.isNull())
                                ? valueNode.asText()
                                : "UNKNOWN";
                    }));
        }

        // If no groupBy → apply sorting (if sortBy provided)
        if (sortBy != null && !sortBy.isEmpty()) {
            Comparator<JsonNode> comparator = (a, b) -> {
                JsonNode va = a.get(sortBy);
                JsonNode vb = b.get(sortBy);

                if (va == null || va.isNull()) return 1;
                if (vb == null || vb.isNull()) return -1;

                // Numeric comparison if both are numbers
                if (va.isNumber() && vb.isNumber()) {
                    return Double.compare(va.asDouble(), vb.asDouble());
                }

                // Otherwise compare as text
                return va.asText().compareToIgnoreCase(vb.asText());
            };

            if ("desc".equalsIgnoreCase(order)) {
                comparator = comparator.reversed();
            }

            jsonNodes.sort(comparator);
        }

        return jsonNodes;
    }
}
