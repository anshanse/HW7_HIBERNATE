package ua.GoIT_Dev2.ProjectManagementSystem.main;

import org.hibernate.Session;
import ua.GoIT_Dev2.ProjectManagementSystem.util.HibernateSessionFactory;
import ua.GoIT_Dev2.ProjectManagementSystem.util.ReadData;
//import ua.GoIT_Dev2.ProjectManagementSystem.controller.handler.HandlerExit;
//import ua.GoIT_Dev2.ProjectManagementSystem.controller.handler.ProjectManagementHandler;
import ua.GoIT_Dev2.ProjectManagementSystem.util.MessageService;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hibernate start");
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        session.getTransaction().commit();
        session.close();

    }
}
