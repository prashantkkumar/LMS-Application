package org.example.batch.FeignClient;

import org.example.batch.DTO.InstructorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "instructor", url = "${instructor.service.url}")
public interface InstructorClient {
    @GetMapping("/instructors/{id}")
    InstructorDTO getInstructorById(@PathVariable("id") Long id);
}
