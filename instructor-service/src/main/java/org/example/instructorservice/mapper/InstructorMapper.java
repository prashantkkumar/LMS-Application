package org.example.instructorservice.mapper;


import org.example.instructorservice.dto.InstructorDTO;
import org.example.instructorservice.entity.Instructor;
import org.springframework.stereotype.Component;

@Component
public class InstructorMapper {
    public InstructorDTO toDTO(Instructor instructor) {
        if (instructor == null) return null;

        return InstructorDTO.builder()
                .id(instructor.getId())
                .name(instructor.getName())
                .employeeId(instructor.getEmployeeId())
                .email(instructor.getEmail())
                .phone(instructor.getPhone())
                .course(instructor.getCourse())
                .build();
    }

    public Instructor toEntity(InstructorDTO dto) {
        if (dto == null) return null;

        return Instructor.builder()
                .id(dto.getId())
                .name(dto.getName())
                .employeeId(dto.getEmployeeId())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .course(dto.getCourse())
                .build();
    }

    public void updateEntity(InstructorDTO dto, Instructor entity) {
        if (dto == null || entity == null) return;

        entity.setName(dto.getName());
        entity.setEmployeeId(dto.getEmployeeId());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
    }

}
