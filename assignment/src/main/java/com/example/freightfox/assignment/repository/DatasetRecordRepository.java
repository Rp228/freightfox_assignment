package com.example.freightfox.assignment.repository;

import com.example.freightfox.assignment.model.DatasetRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DatasetRecordRepository extends JpaRepository<DatasetRecord, Long> {

    List<DatasetRecord> findByDatasetName(String datasetName);

   }


