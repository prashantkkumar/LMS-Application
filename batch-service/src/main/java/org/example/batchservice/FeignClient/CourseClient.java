package org.example.batchservice.FeignClient;


import org.example.batchservice.DTO.CouseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "course", url = "${course.service.url}")
public interface CourseClient {
    @GetMapping("/courses/{id}")
    CouseDTO getCourseById(@PathVariable("id") Long id);
}
