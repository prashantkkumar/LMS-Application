package org.example.batchservice.Controller;


import org.example.batchservice.DTO.BatchDTO;
import org.example.batchservice.Service.BatchService;
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
