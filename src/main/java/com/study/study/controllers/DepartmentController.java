package com.study.study.controllers;

import com.study.study.models.Department;
import com.study.study.models.Employee;
import com.study.study.services.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public Department create(@RequestBody Department department) {
        return departmentService.create(department);
    }

    @GetMapping
    public List<Department> getAll(){
        return departmentService.getAll();
    }
}
