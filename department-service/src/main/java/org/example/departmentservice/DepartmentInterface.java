package org.example.departmentservice;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("QUESTION")
public interface DepartmentInterface {
    @GetMapping("/question")
    String getQuestion();
}
