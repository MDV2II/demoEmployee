package com.example.demoEmployess.RestControllers;

import com.example.demoEmployess.Entity.Employee;
import com.example.demoEmployess.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeesRestContoller {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> findAll() {
    return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);

        if (employee == null) {
            throw new RuntimeException("Employee id not found : " + employeeId);
        }

        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@PathVariable Employee employee) {
        employee.setId(0);
        employeeService.save(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@PathVariable Employee employee) {
        employeeService.save(employee);
        return employee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);
        if (employee == null) {
            throw new RuntimeException("No Employee by ID: " + employeeId);
        }

        employeeService.deleteById(employeeId);
        return "Deleted employee by id: " + employeeId;
    }

}
