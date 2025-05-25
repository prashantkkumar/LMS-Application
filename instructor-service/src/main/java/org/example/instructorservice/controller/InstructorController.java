package org.example.instructorservice.controller;



import jakarta.validation.Valid;
import org.example.instructorservice.dto.InstructorDTO;
import org.example.instructorservice.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @PostMapping("/create")
    public ResponseEntity<InstructorDTO> createInstructor(@Valid @RequestBody InstructorDTO instructorDTO) {
        InstructorDTO created = instructorService.createInstructor(instructorDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<InstructorDTO>> getAllInstructors() {
        List<InstructorDTO> instructors = instructorService.getAllInstructors();
        return ResponseEntity.ok(instructors);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<InstructorDTO> getInstructorById(@PathVariable Long id) {
        Optional<InstructorDTO> instructorOpt = instructorService.getInstructorById(id);
        return instructorOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<InstructorDTO> updateInstructor(@PathVariable Long id,
                                                          @Valid @RequestBody InstructorDTO instructorDTO) {
        Optional<InstructorDTO> updatedOpt = instructorService.updateInstructor(id, instructorDTO);
        return updatedOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        boolean deleted = instructorService.deleteInstructor(id);
        if (deleted) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }
}
