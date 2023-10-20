package com.github.HusseinHamadi.employee.manegment.system.service;

import com.github.HusseinHamadi.employee.manegment.system.entity.Project;
import com.github.HusseinHamadi.employee.manegment.system.error.ProjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
    List<Project> listOfProjects();

    Project getProjectById(Long id) throws ProjectNotFoundException;

    Project createProject(Project project);

    Project updateProject(Long id, Project project) throws ProjectNotFoundException;

    void deleteProject(Long id) throws ProjectNotFoundException;
}
