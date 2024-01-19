package com.github.HusseinHamadi.employee.manegment.system.service;

import com.github.HusseinHamadi.employee.manegment.system.entity.Employee;
import com.github.HusseinHamadi.employee.manegment.system.entity.Project;
import com.github.HusseinHamadi.employee.manegment.system.error.ProjectNotFoundException;
import com.github.HusseinHamadi.employee.manegment.system.repository.EmployeeRepository;
import com.github.HusseinHamadi.employee.manegment.system.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProjectServiceImp implements ProjectService{



    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<Project> listOfProjects() {
        return projectRepository.findAll();
    }

    
    @Override
    public Project getProjectById(Long id) throws ProjectNotFoundException{
        Optional<Project> project=projectRepository.findById(id);
        if(project.isPresent()){
            return project.get();
        }
        else
            throw new ProjectNotFoundException("Project Id doesn't exist");
    }

    @Override
    @Transactional
    public void saveProject(Project project) {
        projectRepository.saveProject(
                project.getName(),
                project.getDescription(),
                project.getCost(),
                project.getStartDate(),
                project.getDepartment().getId()
        );
    }

    @Override
    @Transactional
    public List<Employee> getEmployeesOfProject(Long id) {
        return employeeRepository.getEmployeesOfProject(id);
    }

    @Override
    @Transactional
    public void addEmployeeToProject(Long empId, Long projId) {
        projectRepository.addEmployeeToProject(empId, projId);
    }

    @Override
    @Transactional
    public void updateProject(Long id, Project project) throws ProjectNotFoundException{
        Optional<Project> projOptional=projectRepository.findById(id);
        if(projOptional.isPresent()) {
            Project proj=projOptional.get();
            if (Objects.nonNull(project.getName()) && !"".equalsIgnoreCase(project.getName())) {
                proj.setName(project.getName());
            }
            if (Objects.nonNull(project.getDescription()) && !"".equalsIgnoreCase(project.getDescription())) {
                proj.setDescription(project.getDescription());
            }
            if (Objects.nonNull(project.getCost())) {
                proj.setCost(project.getCost());
            }
            proj.setDepartment(project.getDepartment());
            projectRepository.updateProject(
                    proj.getId(),
                    proj.getName(),
                    proj.getDescription(),
                    proj.getCost(),
                    proj.getStartDate(),
                    proj.getDepartment().getId()
            );
        }
        else
            throw new ProjectNotFoundException("Project Id doesn't exist");
    }

    @Override
    @Transactional
    public void removeEmployee(Long id, Long projId) {
        projectRepository.removeEmployee(id, projId);
    }

    @Override
    @Transactional
    public void deleteProject(Long id) throws ProjectNotFoundException{
        Optional<Project> project = projectRepository.findById(id);
        if(project.isPresent()){
            projectRepository.deleteById(id);
        }
        else{
            throw new ProjectNotFoundException("Project Id doesn't exist");
        }
    }
}
