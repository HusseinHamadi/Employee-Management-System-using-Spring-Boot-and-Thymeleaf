package com.github.HusseinHamadi.employee.manegment.system.service;

import com.github.HusseinHamadi.employee.manegment.system.entity.Project;
import com.github.HusseinHamadi.employee.manegment.system.error.ProjectNotFoundException;
import com.github.HusseinHamadi.employee.manegment.system.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProjectServiceImp implements ProjectService{


    @Autowired
    private ProjectRepository projectRepository;
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
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Long id, Project project) throws ProjectNotFoundException{
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
            return projectRepository.save(proj);
        }
        else
            throw new ProjectNotFoundException("Project Id doesn't exist");
    }

    @Override
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
