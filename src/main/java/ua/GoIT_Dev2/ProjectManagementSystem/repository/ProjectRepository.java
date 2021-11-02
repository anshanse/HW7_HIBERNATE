package ua.GoIT_Dev2.ProjectManagementSystem.repository;

import java.util.List;

public class ProjectRepository extends BaseRepositoryImpl{

    public ProjectRepository(Class modelClass) {
        super(modelClass);
    }

    public List<String> fetchDevsOnProject (int id){
        String sql = "SELECT name FROM developers " +
                "JOIN developers_projects dp ON developers.id = dp.developers_id " +
                "JOIN projects p ON p.id = dp.projects_id " +
                "WHERE projects_id = " + id;
        return sendQuery(sql);
    }

    public List<String> getProjectList(){
        String sql = "SELECT start_date, project_name, count(name) AS 'developers' FROM  projects " +
                "JOIN  developers_projects dp ON  projects.id = dp.projects_id " +
                "JOIN  developers d on d.id = dp.developers_id " +
                "GROUP BY  project_name ORDER BY project_name asc;";
        return sendQuery(sql);
    }
}
