package com.study.study.controllers;

import com.study.study.models.Department;
import com.study.study.models.Employee;
import com.study.study.services.DepartmentService;
import com.study.study.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @GetMapping
    public List<Employee> getAll(){
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Integer id) {
        Employee employee = employeeService.getById(id);
        Department department = employee.getDepartment();

        Map<String, Object> response = new HashMap<>();
        response.put("employee", employee);

        Map<String, Object> departmentMap = new HashMap<>();
        departmentMap.put("id", department != null ? department.getId() : null);
        departmentMap.put("name", department != null ? department.getName() : null);
        response.put("department", departmentMap);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/setdepartment/{d_id}/{e_id}")
    public ResponseEntity<Map<String, Object>> setDepartment(@PathVariable Integer d_id, @PathVariable Integer e_id) {
        Employee employee = employeeService.getById(e_id);
        Department department = departmentService.getById(d_id);
        employee.setDepartment(department);

        employeeService.create(employee);

        //JSON object ตอบกลับ
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Department assigned successfully");

        return ResponseEntity.ok(response);
    }


}
