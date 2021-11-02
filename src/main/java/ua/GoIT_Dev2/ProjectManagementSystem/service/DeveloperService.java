package ua.GoIT_Dev2.ProjectManagementSystem.service;

import ua.GoIT_Dev2.ProjectManagementSystem.model.Developer;
import ua.GoIT_Dev2.ProjectManagementSystem.repository.DevRepository;

import java.util.List;

public class DeveloperService {

    private final DevRepository repository = new DevRepository(Developer.class);

    public List<String> getSumSalaryDevsOnProject (int id){
        return repository.sumSalaryDevsOnProject(id);
    }

    public List<String> getDevWithSkill (String skill){
        return repository.devWithSkill(skill);
    }

    public List<String> getDevWithGrade (String grade){
        return repository.devWithGrade(grade);
    }
}
