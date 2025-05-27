package org.example.batch.FeignClient;


import org.example.batch.DTO.StudentDTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Student", url = "${instructor.service.url}")
public interface StudentClient {
    @GetMapping("/students/{id}")
    StudentDTo getStudentById(@PathVariable("id") Long id);
}
