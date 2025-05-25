package org.example.departmentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    DepartmentInterface departmentInterface;

    @GetMapping("/get")
    public String get() {
        return departmentInterface.getQuestion();
    }
}
