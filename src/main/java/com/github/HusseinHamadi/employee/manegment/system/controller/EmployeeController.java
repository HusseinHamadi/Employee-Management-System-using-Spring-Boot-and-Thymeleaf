package com.github.HusseinHamadi.employee.manegment.system.controller;

import com.github.HusseinHamadi.employee.manegment.system.entity.Department;
import com.github.HusseinHamadi.employee.manegment.system.entity.Employee;
import com.github.HusseinHamadi.employee.manegment.system.error.EmployeeNotFoundException;
import com.github.HusseinHamadi.employee.manegment.system.service.DepartmentService;
import com.github.HusseinHamadi.employee.manegment.system.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Controller
@RequestMapping("/company/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;


    //List of Employees
    @GetMapping
    public String getEmployees(Model model){

        List<Employee> employees=employeeService.listOfEmployees();
        model.addAttribute("employee", employees);
        return "employees";
    }


    //Search Employee by ID
    @GetMapping("/getEmployeeById")
    public String getEmployeeById(@RequestParam("employeeId") String employeeId, Model model) throws EmployeeNotFoundException {
        Long empId= Long.parseLong(employeeId);
        Employee employee = employeeService.getEmployeeById(empId);
        model.addAttribute("employee", employee);
        return "employeeSearchResult";
    }


    //List of Employees in a Department
    @GetMapping("/getEmployeeByDepartmentId")
    public String getEmployeeByDepartmentId(@RequestParam("depId") String depId, Model model) throws EmployeeNotFoundException {
        Long id=Long.parseLong(depId);
        List<Employee> employees = employeeService.getEmployeeByDepartmentId(id);
        model.addAttribute("employees", employees);
        return "employeeSearchResult";
    }

    //List of Employees in a Salary Range
    @GetMapping("/getEmployeeBySalary")
    public String getEmployeeBySalary(@RequestParam("num1") String num1, @RequestParam("num2") String num2, Model model) throws EmployeeNotFoundException {
        Double up=Double.parseDouble(num1);
        Double low=Double.parseDouble(num2);
//        if(up<low){
//            Double aux=up;
//            up=low;
//            low=aux;
//        }
        List<Employee> employees = employeeService.getEmployeeBySalary(up,low);

        model.addAttribute("upperBound", up);
        model.addAttribute("lowerBound", low);

        model.addAttribute("employeeSalaries", employees);
        return "employeeSearchResult";
    }

    //Creating an employee and sending it to the form to be filled
    @GetMapping("/new")
    public String addEmployeeForm(Model model) {
        List<Department> departments = departmentService.listOfDepartments();
        model.addAttribute("departments", departments);
        model.addAttribute("employee", new Employee());
        return "newEmployee";
    }

    // Handle form submission
    @PostMapping("/createEmployee")
    public String addEmployee(@ModelAttribute Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/company/employees";
    }

    @GetMapping("/update/{id}")
    public String updateEmployeeForm(@PathVariable("id") Long id, Model model) throws EmployeeNotFoundException {
        Employee employee=employeeService.getEmployeeById(id);
        List<Department> departments = departmentService.listOfDepartments();
        model.addAttribute("departments", departments);
        model.addAttribute("employee", employee);
        return "/updateEmployee";
    }
    @PostMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable("id") Long id, @ModelAttribute("employee") Employee employee) throws EmployeeNotFoundException{
        employeeService.updateEmployee(id, employee);

        return "redirect:/company/employees";
    }


    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) throws EmployeeNotFoundException{

        try {
            // Delete the employee
            employeeService.deleteEmployee(id);
        } catch (EmployeeNotFoundException e) {
            // Handle the exception (e.g., log or show an error message)
            e.printStackTrace();
        }
        return "redirect:/company/employees";
    }

}
