package com.example.freightfox.assignment.controller;

import com.example.freightfox.assignment.service.DatasetService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dataset")
public class DatasetController {

    @Autowired
    private DatasetService datasetService;

    // Insert a record into a dataset
    @PostMapping("/{datasetName}")
    public Map<String, Object> insertRecord(
            @PathVariable String datasetName,
            @RequestBody String json
    ) {
        Long id = datasetService.insertRecord(datasetName, json);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Record added successfully");
        response.put("dataset", datasetName);
        response.put("recordId", id);

        return response;
    }


    // Query records
    @GetMapping("/{datasetName}")
    public Object queryRecords(
            @PathVariable String datasetName,
            @RequestParam(required = false) String groupBy,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order
    ) {
        return datasetService.query(datasetName, groupBy, sortBy, order);
    }
}
