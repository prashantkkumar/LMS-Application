package org.example.batch.Controller;

import org.example.batch.DTO.BatchDTO;
import org.example.batch.Service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batches")
public class BatchController {

    @Autowired
    private BatchService batchService;


    @GetMapping("/{id}")
    public ResponseEntity<BatchDTO> getBatchWithDetails(@PathVariable Long id) {
        BatchDTO batchDTO = batchService.getBatchById(id);
        return ResponseEntity.ok(batchDTO);
    }
}
