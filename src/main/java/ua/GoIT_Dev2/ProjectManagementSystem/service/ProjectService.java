package ua.GoIT_Dev2.ProjectManagementSystem.service;

import ua.GoIT_Dev2.ProjectManagementSystem.model.Project;
import ua.GoIT_Dev2.ProjectManagementSystem.repository.ProjectRepository;

import java.util.List;

public class ProjectService {

    private final ProjectRepository repository = new ProjectRepository(Project.class);

    public List<String> getDevsOnProject (int id){
        return repository.fetchDevsOnProject(id);
    }

    public List<String> getProjectList(){
        return repository.getProjectList();
    }
}
