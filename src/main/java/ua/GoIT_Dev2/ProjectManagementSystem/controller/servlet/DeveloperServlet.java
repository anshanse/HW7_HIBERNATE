package ua.GoIT_Dev2.ProjectManagementSystem.controller.servlet;

import ua.GoIT_Dev2.ProjectManagementSystem.model.BaseEntity;
import ua.GoIT_Dev2.ProjectManagementSystem.model.Company;
import ua.GoIT_Dev2.ProjectManagementSystem.model.Developer;
import ua.GoIT_Dev2.ProjectManagementSystem.service.BaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.List;

@WebServlet("/developer/*")
public class DeveloperServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = -5518363074971978271L;

    private final BaseService service = new BaseService();
    private final Class className = Developer.class;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if ("/show".equals(pathInfo) || "/".equals(pathInfo) || pathInfo == null){
            List<Developer> entities = service.getAll(className);
            req.setAttribute("entities", entities);
            req.getRequestDispatcher("/view/developer/show.jsp").forward(req,resp);
        } else
        if (pathInfo.split("/").length!=2){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else
        if ("/find".equals(pathInfo)) {
            if (req.getParameter("entityId") != null) {
                BaseEntity entity = service.getEntity(className, req.getParameter("entityId"), req.getParameter("entityName"));
                if (entity == null) {
                    String message = ("Developer with input data not exists");
                    req.setAttribute("message", message);
                } else {
                    req.setAttribute("entity", entity);
                }
            }
            req.getRequestDispatcher("/view/developer/find.jsp").forward(req, resp);
        } else
        if ("/updateFind".equals(pathInfo)) {
            if (req.getParameter("entityId") != null) {
                BaseEntity entity = service.getEntity(className, req.getParameter("entityId"), req.getParameter("entityName"));
                if (entity == null) {
                    String message = ("Company with input data not exists");
                    req.setAttribute("message", message);
                } else {
                    req.setAttribute("entity", entity);
                }
            }
            req.getRequestDispatcher("/view/developer/update.jsp").forward(req, resp);
        } else
        if ("/get".equals(pathInfo)){
            Developer entity = (Developer) service.read(className,Long.valueOf(req.getParameter("id")));
            req.setAttribute("entity", entity);
            req.getRequestDispatcher("/view/developer/details.jsp").forward(req, resp);
        } else
        if ("/create".equals(pathInfo)){
            req.getRequestDispatcher("/view/developer/create.jsp").forward(req,resp);
        } else
        if ("/update".equals(pathInfo)){
            if (req.getParameter("entityId") != null) {
                doPut(req, resp);
            }
            req.getRequestDispatcher("/view/developer/update.jsp").forward(req,resp);
        } else
        if ("/delete".equals(pathInfo)){
            if (req.getParameter("entityId") != null) {
                doDelete(req, resp);
            }
            req.getRequestDispatcher("/view/developer/delete.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        req.setCharacterEncoding("UTF-8");
        if ("/create".equals(pathInfo)){
            if (!service.ifExist(className,req)){
                Developer newEntity = Developer.builder()
                        .name(req.getParameter("name"))
                        .age(Integer.parseInt(req.getParameter("age")))
                        .sex(req.getParameter("sex"))
                        .salary(Long.valueOf(req.getParameter("salary")))
                        .info(req.getParameter("info"))
                        .build();
                req.setAttribute("entity", service.save(className, newEntity));
            }else{
                req.setAttribute("existCompany", service.findByName(className, req.getParameter("name")).get(0));
            }
            req.getRequestDispatcher("/view/developer/create.jsp").forward(req,resp);

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        req.setCharacterEncoding("UTF-8");
        if ("/update".equals(pathInfo)){
            Developer entity = (Developer) service.read(className, req.getParameter("entityId"));
            entity.setName(req.getParameter("entityName"));
            entity.setAge(Integer.valueOf(req.getParameter("age")));
            entity.setSex(req.getParameter("sex"));
            entity.setSalary(Long.valueOf(req.getParameter("salary")));
            entity.setInfo(req.getParameter("info"));
            service.save(className, entity);
            req.setAttribute("message", "Data updated");
        }
        req.getRequestDispatcher("/view/developer/update.jsp").forward(req,resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        req.setCharacterEncoding("UTF-8");
        if ("/delete".equals(pathInfo)){
            Developer entity = (Developer) service.read(className,Long.valueOf(req.getParameter("entityId")));
            req.setAttribute("entity", entity);
            String message = " was deleted";
            req.setAttribute("message", message);
            service.delete(className,Long.valueOf(req.getParameter("entityId")));
            req.getRequestDispatcher("/view/developer/delete.jsp").forward(req,resp);
        }
    }
}
