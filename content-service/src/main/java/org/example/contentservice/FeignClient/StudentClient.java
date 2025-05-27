package org.example.contentservice.FeignClient;

import org.example.contentservice.ContentDTO.StudentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Student", url = "${instructor.service.url}")
public interface StudentClient {
    @GetMapping("/students/{id}")
    StudentDTO getStudentById(@PathVariable("id") Long id);
}
