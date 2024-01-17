package com.github.HusseinHamadi.employee.manegment.system.controller;

import com.github.HusseinHamadi.employee.manegment.system.entity.Department;
import com.github.HusseinHamadi.employee.manegment.system.entity.Employee;
import com.github.HusseinHamadi.employee.manegment.system.entity.Project;
import com.github.HusseinHamadi.employee.manegment.system.error.DepartmentNotFoundException;
import com.github.HusseinHamadi.employee.manegment.system.error.EmployeeNotFoundException;
import com.github.HusseinHamadi.employee.manegment.system.error.ProjectNotFoundException;
import com.github.HusseinHamadi.employee.manegment.system.service.DepartmentService;
import com.github.HusseinHamadi.employee.manegment.system.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/company/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String getProjects(Model model){

        List<Project> projects=projectService.listOfProjects();
        model.addAttribute("project", projects);
        return "projects";
    }

    @GetMapping("/company/projects/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") Long id) throws ProjectNotFoundException {
        return new ResponseEntity<Project>(projectService.getProjectById(id), HttpStatus.OK);
    }

    @GetMapping("/new")
    public String addProjectForm(Model model) {
        List<Department> departments = departmentService.listOfDepartments();
        model.addAttribute("departments", departments);
        model.addAttribute("project", new Project());
        return "projectsForm";
    }

    @PostMapping("/create")
    public String addProject(@ModelAttribute Project project) {
        projectService.saveProject(project);
        return "redirect:/company/projects";
    }

    @GetMapping("/employees/{id}")
    public String projectEmployees(@PathVariable("id") Long id, Model model){
        List<Employee> employees=projectService.getEmployeesOfProject(id);
        model.addAttribute("employees", employees);
        model.addAttribute("project", id);
        return "projectEmployees";
    }


    @GetMapping("/employees/add/{projId}")
    public String addEmployeeToProject(@RequestParam("employeeId") Long employeeId, @PathVariable Long projId,  Model model){

        projectService.addEmployeeToProject(employeeId, projId);

        return "redirect:/company/projects/employees/"+projId;
    }

    @GetMapping("/update/{id}")
    public String updateProjectForm(@PathVariable("id") Long id, Model model) throws ProjectNotFoundException, DepartmentNotFoundException {
        Project project= projectService.getProjectById(id);
        List<Department> departments = departmentService.listOfDepartments();
        model.addAttribute("departments", departments);
        model.addAttribute("project", project);
        return "projectUpdate";
    }

    @PostMapping("/updateProject/{projId}")
    public String updateProject(@PathVariable("projId") Long projId, @ModelAttribute("project") Project project) throws ProjectNotFoundException {

        projectService.updateProject(projId, project);
        return "redirect:/company/projects";
    }

    @GetMapping("/employees/remove/{empId}/{projId}")
    public String removeEmployee(@PathVariable("empId") Long empId, @PathVariable("projId") Long projId){
        projectService.removeEmployee(empId, projId);
        return "redirect:/company/projects/employees/"+projId;
    }


    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id) throws EmployeeNotFoundException {

        try {
            // Delete the employee
            projectService.deleteProject(id);
        } catch (ProjectNotFoundException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/company/projects";
    }
}
