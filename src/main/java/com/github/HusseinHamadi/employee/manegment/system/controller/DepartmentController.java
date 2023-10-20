package com.github.HusseinHamadi.employee.manegment.system.controller;

import com.github.HusseinHamadi.employee.manegment.system.entity.Department;
import com.github.HusseinHamadi.employee.manegment.system.entity.Employee;
import com.github.HusseinHamadi.employee.manegment.system.error.DepartmentNotFoundException;
import com.github.HusseinHamadi.employee.manegment.system.error.EmployeeNotFoundException;
import com.github.HusseinHamadi.employee.manegment.system.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/company/departments")
    public ResponseEntity<List<Department>> listOfDepartments(){
        return new ResponseEntity<List<Department>>(departmentService.listOfDepartments(), HttpStatus.OK);
    }

    @GetMapping("/company/departments/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long id) throws DepartmentNotFoundException {
        return new ResponseEntity<Department>(departmentService.getDepartmentById(id), HttpStatus.OK);
    }

    @PostMapping("/company/departments")
    public ResponseEntity<Department> createDepartment(@Valid @RequestBody Department department){
        return new ResponseEntity<Department>(departmentService.createDepartment(department), HttpStatus.CREATED);
    }

    @PutMapping("/company/departments/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") Long id, @RequestBody Department department) throws DepartmentNotFoundException, EmployeeNotFoundException {
        return new ResponseEntity<Department>(departmentService.updateDepartment(id, department), HttpStatus.OK);
    }


    @DeleteMapping("/company/departments/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long id) throws DepartmentNotFoundException {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<String >("Department Deleted Successfully", HttpStatus.OK);
    }


}
