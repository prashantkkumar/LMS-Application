package org.example.instructorservice.service;


import org.example.instructorservice.dto.InstructorDTO;
import org.example.instructorservice.entity.Instructor;
import org.example.instructorservice.mapper.InstructorMapper;
import org.example.instructorservice.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private InstructorMapper instructorMapper;

    public InstructorDTO createInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = instructorMapper.toEntity(instructorDTO);
        Instructor saved = instructorRepository.save(instructor);
        return instructorMapper.toDTO(saved);
    }

    public List<InstructorDTO> getAllInstructors() {
        List<Instructor> instructors = instructorRepository.findAll();
        return instructors.stream()
                .map(instructorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<InstructorDTO> getInstructorById(Long id) {
        return instructorRepository.findById(id)
                .map(instructorMapper::toDTO);
    }

    public Optional<InstructorDTO> updateInstructor(Long id, InstructorDTO instructorDTO) {
        return instructorRepository.findById(id).map(existing -> {
            instructorMapper.updateEntity(instructorDTO, existing);
            Instructor updated = instructorRepository.save(existing);
            return instructorMapper.toDTO(updated);
        });
    }

    public boolean deleteInstructor(Long id) {
        return instructorRepository.findById(id).map(instructor -> {
            instructorRepository.delete(instructor);
            return true;
        }).orElse(false);
    }

}
