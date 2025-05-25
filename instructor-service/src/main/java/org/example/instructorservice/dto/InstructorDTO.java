package org.example.instructorservice.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstructorDTO {
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Employee ID is mandatory")
    private String employeeId;

    @Email(message = "Email should be valid")
    private String email;

    private String phone;

    @NotBlank(message = "Course is mandatory")
    private String course;
}
