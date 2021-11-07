package ua.GoIT_Dev2.ProjectManagementSystem.util;

import lombok.SneakyThrows;
import org.hibernate.Session;
import ua.GoIT_Dev2.ProjectManagementSystem.model.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PreparingDB {
    Session session = HibernateSessionFactory.getSessionFactory().openSession();

    @SneakyThrows
    public void filling (){
        session.beginTransaction();
        //Company section
        Company company1 = Company.builder().city("Kiev").name("CompanyName1").build();
        Company company2 = Company.builder().name("CompanyName2").city("Kharkov").build();
        //End Company section

        //Customer section
        Customer customer1 = Customer.builder().name("Customer1").build();
        Customer customer2 = Customer.builder().name("Customer2").build();
        Customer customer3 = Customer.builder().name("Customer3").build();
        //End Customer Section

        //Skills section
        Skill skill1 = Skill.builder().name("Java").grade("Junior").build();
        Skill skill2 = Skill.builder().name("Java").grade("Middle").build();
        Skill skill3 = Skill.builder().name("Java").grade("Senior").build();
        Skill skill4 = Skill.builder().name("HTML").grade("Junior").build();
        Skill skill5 = Skill.builder().name("HTML").grade("Middle").build();
        Skill skill6 = Skill.builder().name("HTML").grade("Senior").build();
        Set<Skill> developerSkills1 = new HashSet<>();
        developerSkills1.add(skill1);
        developerSkills1.add(skill6);
        Set<Skill> developerSkills2 = new HashSet<>();
        developerSkills2.add(skill2);
        developerSkills2.add(skill4);
        Set<Skill> developerSkills3 = new HashSet<>();
        developerSkills3.add(skill3);
        developerSkills3.add(skill5);
        //End Skill section

        //Project section
        Set<Project> projectSet1 = new HashSet<>();
        Set<Project> projectSet2 = new HashSet<>();
        Project project1 = Project.builder()
                .name("Project1")
                .company(company1)
                .customer(customer1)
                .cost(50_000)
                .startDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-15"))
                .build();
        Project project2 = Project.builder()
                .name("Project21")
                .company(company1)
                .customer(customer2)
                .cost(55_000)
                .startDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-12"))
                .build();
        Project project3 = Project.builder()
                .name("Project3")
                .company(company2)
                .customer(customer2)
                .cost(45_000)
                .startDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-15"))
                .build();
        projectSet1.add(project1);
        projectSet1.add(project3);
        projectSet2.add(project2);
        projectSet2.add(project3);
        //End Project section

        //Developer section
        Set<Developer> developers1 = new HashSet<>();
        Set<Developer> developers2 = new HashSet<>();
        Developer developer1 = Developer.builder().name("Dev1").age(25).sex("m").salary(1_500L).company(company1).projects(projectSet1).skills(developerSkills1).build();
        Developer developer2 = Developer.builder().name("Dev2").age(35).sex("f").salary(2_500L).company(company2).projects(projectSet1).skills(developerSkills3).build();
        Developer developer3 = Developer.builder().name("Dev3").age(28).sex("m").salary(2_000L).company(company1).projects(projectSet2).skills(developerSkills2).build();
        developers1.add(developer1);
        developers1.add(developer2);
        developers2.add(developer3);
        developers2.add(developer1);
        //End developer section

        customer1.setProjects(projectSet1);
        customer2.setProjects(projectSet2);

        company1.setDevelopers(developers1);
        company2.setDevelopers(developers2);
        company1.setProjects(projectSet1);
        company2.setProjects(projectSet2);

        project1.setDevelopers(developers1);
        project2.setDevelopers(developers2);
        project3.setDevelopers(developers1);

        session.persist(company1);
        session.save(company2);
        session.save(customer3);

        session.getTransaction().commit();
        session.close();
    }

}
