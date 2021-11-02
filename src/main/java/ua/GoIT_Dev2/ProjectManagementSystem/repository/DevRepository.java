package ua.GoIT_Dev2.ProjectManagementSystem.repository;

import java.util.List;

public class DevRepository extends BaseRepositoryImpl{

    public DevRepository(Class modelClass) {
        super(modelClass);
    }

    public List<String> sumSalaryDevsOnProject (int id){
        String sql = "SELECT  SUM(salary) AS 'Summary salary' FROM developers " +
                "JOIN developers_projects dp ON developers.id = dp.developers_id " +
                "JOIN projects p ON p.id = dp.projects_id " +
                "WHERE projects_id = " + id;
        return sendQuery(sql);
    }

    public List<String> devWithSkill (String skill){
        String sql = "SELECT name FROM developers " +
                "JOIN developers_skills ds ON developers.id = ds.developers_id " +
                "JOIN skills s ON s.id = ds.skills_id  " +
                "WHERE skill = '" + skill + "' ORDER BY name asc";
        return sendQuery(sql);
    }

    public List<String> devWithGrade (String grade) {
        String sql = "SELECT DISTINCT name FROM developers " +
                "JOIN developers_skills ds ON developers.id = ds.developers_id " +
                "JOIN skills s ON s.id = ds.skills_id  " +
                "WHERE grade = '" + grade + "' ORDER BY name asc";
        return sendQuery(sql);
    }
}
