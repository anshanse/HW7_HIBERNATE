package ua.GoIT_Dev2.ProjectManagementSystem.controller.servlet;

import lombok.SneakyThrows;
import ua.GoIT_Dev2.ProjectManagementSystem.model.BaseEntity;
import ua.GoIT_Dev2.ProjectManagementSystem.model.Company;
import ua.GoIT_Dev2.ProjectManagementSystem.model.Project;
import ua.GoIT_Dev2.ProjectManagementSystem.service.BaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@WebServlet("/project/*")
public class ProjectServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 5372227354208871534L;

    private final BaseService service = new BaseService();
    private final Class className = Project.class;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if ("/show".equals(pathInfo) || "/".equals(pathInfo) || pathInfo == null){
            List<Project> entities = service.getAll(className);
            req.setAttribute("entities", entities);
            req.getRequestDispatcher("/view/project/show.jsp").forward(req,resp);
        } else
        if (pathInfo.split("/").length!=2){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else
        if ("/find".equals(pathInfo)) {
            if (req.getParameter("entityId") != null) {
                BaseEntity entity = service.getEntity(className, req.getParameter("entityId"), req.getParameter("entityName"));
                if (entity == null) {
                    String message = ("Project with input data not exists");
                    req.setAttribute("message", message);
                } else {
                    req.setAttribute("entity", entity);
                }
            }
            req.getRequestDispatcher("/view/project/find.jsp").forward(req, resp);
        } else
        if ("/updateFind".equals(pathInfo)) {
            if (req.getParameter("entityId") != null) {
                BaseEntity entity = service.getEntity(className, req.getParameter("entityId"), req.getParameter("entityName"));
                if (entity == null) {
                    String message = ("Project with input data not exists");
                    req.setAttribute("message", message);
                } else {
                    req.setAttribute("entity", entity);
                }
            }
            req.getRequestDispatcher("/view/project/update.jsp").forward(req, resp);
        } else
        if ("/get".equals(pathInfo)){
            Project entity = (Project) service.read(className,Long.valueOf(req.getParameter("id")));
            req.setAttribute("entity", entity);
            req.getRequestDispatcher("/view/project/details.jsp").forward(req, resp);
        } else
        if ("/create".equals(pathInfo)){
            req.getRequestDispatcher("/view/project/create.jsp").forward(req,resp);
        } else
        if ("/update".equals(pathInfo)){
            if (req.getParameter("entityId") != null) {
                doPut(req, resp);
            }
            req.getRequestDispatcher("/view/project/update.jsp").forward(req,resp);
        } else
        if ("/delete".equals(pathInfo)){
            if (req.getParameter("entityId") != null) {
                doDelete(req, resp);
            }
            req.getRequestDispatcher("/view/project/delete.jsp").forward(req,resp);
        }
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        req.setCharacterEncoding("UTF-8");
        if ("/create".equals(pathInfo)){
            if (!service.ifExist(className,req)){
                Project newEntity = Project.builder()
                        .name(req.getParameter("name"))
                        .cost(Integer.valueOf(req.getParameter("cost")))
                        .startDate(new SimpleDateFormat("yyy-MM-DD").parse(req.getParameter("startDate")))
                        .build();
                req.setAttribute("entity", service.save(className, newEntity));
            }else{
                req.setAttribute("existEntity", service.findByName(className, req.getParameter("name")).get(0));
            }
            req.getRequestDispatcher("/view/project/create.jsp").forward(req,resp);

        }
    }

    @SneakyThrows
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        req.setCharacterEncoding("UTF-8");
        if ("/update".equals(pathInfo)){
            Project entity = (Project) service.read(className, Long.valueOf(req.getParameter("entityId")));
            entity.setName(req.getParameter("entityName"));
            entity.setCost(Integer.valueOf(req.getParameter("cost")));
            entity.setStartDate(new SimpleDateFormat("yyy-MM-DD").parse(req.getParameter("startDate")));
            service.save(className, entity);
            req.setAttribute("message", "Data updated");
        }
        req.getRequestDispatcher("/view/project/update.jsp").forward(req,resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        req.setCharacterEncoding("UTF-8");
        if ("/delete".equals(pathInfo)){
            Project entity = (Project) service.read(className,Long.valueOf(req.getParameter("entityId")));
            req.setAttribute("entity", entity);
            String message = " was deleted";
            req.setAttribute("message", message);
            service.delete(className,Long.valueOf(req.getParameter("entityId")));
            req.getRequestDispatcher("/view/project/delete.jsp").forward(req,resp);
        }
    }
}
