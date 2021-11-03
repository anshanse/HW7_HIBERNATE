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
        Company company1 = Company.builder().city("Kiev").name("CompanyName").build();
        Company company2 = Company.builder().name("Kharkov").city("CompanyCity2").build();
        //End Company section

        //Customer section
        Customer customer1 = Customer.builder().name("Customer1").build();
        Customer customer2 = Customer.builder().name("Customer1").build();
        //Customer customer3 = Customer.builder().name("Customer1").build();
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
        Set<Project> projects1 = new HashSet<>();
        Set<Project> projects2 = new HashSet<>();
        Project project1 = Project.builder()
                .name("Project1")
                .company(company1)
                .customer(customer1)
                .startDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-15"))
                .build();
        Project project2 = Project.builder()
                .name("Project21")
                .company(company1)
                .customer(customer2)
                .startDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-12"))
                .build();
        Project project3 = Project.builder()
                .name("Project3")
                .company(company2)
                .customer(customer2)
                .startDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-15"))
                .build();
        projects1.add(project1);
        projects1.add(project3);
        projects2.add(project2);
        projects2.add(project3);
        //End Project section

        company1.setProjects(projects1);
        company2.setProjects(projects2);

        //Developer section
        Developer developer1 = Developer.builder().name("Dev1").age(25).sex("m").salary(1_500L).company(company1).projects(projects1).skills(developerSkills1).build();
        Developer developer2 = Developer.builder().name("Dev2").age(35).sex("f").salary(2_500L).company(company2).projects(projects1).skills(developerSkills3).build();
        Developer developer3 = Developer.builder().name("Dev3").age(28).sex("m").salary(2_000L).company(company1).projects(projects2).skills(developerSkills2).build();
        //End developer section

        customer1.setProjects(projects1);
        customer2.setProjects(projects2);

        session.save(company1);
        session.save(company2);

        session.getTransaction().commit();
        session.close();
    }

}
