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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/company/departments")
    public String getDepartments(Model model){

        List<Department> departments=departmentService.listOfDepartments();
        model.addAttribute("department", departments);
        return "department";
    }

    @GetMapping("/company/departments/{id}")
    public String department(@PathVariable Long id, Model model) throws DepartmentNotFoundException {
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        return "department";
    }


    @GetMapping("/company/departments/new")
    public String showDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        model.addAttribute("employee", true);
        return "department-form";
    }

    @PostMapping("/company/departments")
    public String createDepartment(@ModelAttribute Department department) {
        departmentService.createDepartment(department);
        return "redirect:/company/departments";
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
