package com.github.HusseinHamadi.employee.manegment.system.service;

import com.github.HusseinHamadi.employee.manegment.system.entity.Employee;
import com.github.HusseinHamadi.employee.manegment.system.entity.Project;
import com.github.HusseinHamadi.employee.manegment.system.error.ProjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
    List<Project> listOfProjects();

    Project getProjectById(Long id) throws ProjectNotFoundException;

    void updateProject(Long id, Project project) throws ProjectNotFoundException;

    void deleteProject(Long id) throws ProjectNotFoundException;

    void saveProject(Project project);

    List<Employee> getEmployeesOfProject(Long id);

    void addEmployeeToProject(Long empId, Long projId);

    void removeEmployee(Long id, Long projId);
}
