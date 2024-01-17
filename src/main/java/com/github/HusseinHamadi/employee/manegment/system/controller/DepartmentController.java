package com.github.HusseinHamadi.employee.manegment.system.controller;

import com.github.HusseinHamadi.employee.manegment.system.entity.Department;
import com.github.HusseinHamadi.employee.manegment.system.entity.Employee;
import com.github.HusseinHamadi.employee.manegment.system.error.DepartmentNotFoundException;
import com.github.HusseinHamadi.employee.manegment.system.error.EmployeeNotFoundException;
import com.github.HusseinHamadi.employee.manegment.system.service.DepartmentService;
import com.github.HusseinHamadi.employee.manegment.system.service.EmployeeService;
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
@RequestMapping("/company/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;


    @GetMapping
    public String getDepartments(Model model){

        List<Department> departments=departmentService.listOfDepartments();
        model.addAttribute("department", departments);
        return "department";
    }

    @GetMapping("/getDepartmentById")
    public String department(@RequestParam("id") Long id, Model model) throws DepartmentNotFoundException {
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        return "departmentSearchResult";
    }


    @GetMapping("/new")
    public String showDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        model.addAttribute("manager", new Employee());
        return "department-form";
    }

    @PostMapping("/create")
    public String createDepartmentWithManager(@ModelAttribute Department department, @ModelAttribute Employee manager) {

//            employeeService.saveEmployee(manager);
            departmentService.createDepartmentWithManager(department, manager);
//            manager.setDepartment(department);
//            department.setManager(manager);



        return "redirect:/company/departments";
    }

    @GetMapping("/update/{id}")
    public String updateDepartmentForm(@PathVariable("id") Long id, Model model) throws DepartmentNotFoundException, EmployeeNotFoundException {
        Department department= departmentService.getDepartmentById(id);

        Employee manager=department.getManager();

        model.addAttribute("department", department);
        model.addAttribute("manager", manager);
        return "departmentUpdate";
    }

    @PostMapping("/updateDepartment/{depId}/{mgrId}")
    public String updateDepartment(@PathVariable("depId") Long depId, @ModelAttribute("department") Department department, @PathVariable("mgrId") Long mgrId, @ModelAttribute("manager") Employee manager) throws DepartmentNotFoundException, EmployeeNotFoundException {
        departmentService.updateDepartment(depId, department, mgrId, manager);
        return "redirect:/company/departments";
    }


    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable("id") Long id) throws DepartmentNotFoundException {
        departmentService.deleteDepartment(id);
        return "redirect:/company/departments";
    }


}
