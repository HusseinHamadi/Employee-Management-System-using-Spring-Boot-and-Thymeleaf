package com.github.HusseinHamadi.employee.manegment.system.controller;

import com.github.HusseinHamadi.employee.manegment.system.entity.Employee;
import com.github.HusseinHamadi.employee.manegment.system.error.EmployeeNotFoundException;
import com.github.HusseinHamadi.employee.manegment.system.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/company/employees")
    public ResponseEntity<List<Employee>> fetchEmployeeList(){

        return new ResponseEntity<List<Employee>>(employeeService.listOfEmployees(), HttpStatus.OK);
    }

    @GetMapping("/company/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @PostMapping("/company/employees/create/{depId}")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee, @PathVariable(required = false) Long depId){
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee, depId),HttpStatus.CREATED);
    }

    @PutMapping("/company/employees")
    public ResponseEntity<Employee> updateEmployee(@RequestParam Long id, @RequestBody Employee employee) throws EmployeeNotFoundException{
        return new ResponseEntity<Employee>(employeeService.updateEmployee(id, employee),HttpStatus.OK);
    }

    @DeleteMapping("/company/employees")
    public ResponseEntity<String> deleteEmployee(@RequestParam Long id) throws EmployeeNotFoundException{
        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Employee Deleted Successfully",HttpStatus.OK);
    }

}
