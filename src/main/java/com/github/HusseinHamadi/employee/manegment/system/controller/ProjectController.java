package com.github.HusseinHamadi.employee.manegment.system.controller;

import com.github.HusseinHamadi.employee.manegment.system.entity.Project;
import com.github.HusseinHamadi.employee.manegment.system.error.ProjectNotFoundException;
import com.github.HusseinHamadi.employee.manegment.system.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/company/projects")
    public ResponseEntity<List<Project>> listOfProjects(){
        return new ResponseEntity<List<Project>>(projectService.listOfProjects(), HttpStatus.OK);
    }

    @GetMapping("/company/projects/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") Long id) throws ProjectNotFoundException {
        return new ResponseEntity<Project>(projectService.getProjectById(id), HttpStatus.OK);
    }

    @PostMapping("/company/projects")
    public ResponseEntity<Project> createProject(@Valid @RequestBody Project project){
        return new ResponseEntity<Project>(projectService.createProject(project), HttpStatus.CREATED);
    }

    @PutMapping("/company/projects/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") Long id, Project project) throws ProjectNotFoundException {
        return new ResponseEntity<Project>(projectService.updateProject(id, project), HttpStatus.OK);
    }


    @DeleteMapping("/company/projects/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable("id") Long id) throws ProjectNotFoundException {
        projectService.deleteProject(id);
        return new ResponseEntity<String >("Project Deleted Successfully", HttpStatus.OK);
    }
}
