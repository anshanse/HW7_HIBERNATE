package ua.GoIT_Dev2.ProjectManagementSystem.controller.servlet;

import ua.GoIT_Dev2.ProjectManagementSystem.model.BaseEntity;
import ua.GoIT_Dev2.ProjectManagementSystem.model.Company;
import ua.GoIT_Dev2.ProjectManagementSystem.model.Skill;
import ua.GoIT_Dev2.ProjectManagementSystem.service.BaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.List;

@WebServlet("/skill/*")
public class SkillServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = -5518363074971978271L;

    private final BaseService service = new BaseService();
    private final Class className = Skill.class;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if ("/show".equals(pathInfo) || "/".equals(pathInfo) || pathInfo == null){
            List<Skill> entities = service.getAll(className);
            req.setAttribute("entities", entities);
            req.getRequestDispatcher("/view/skill/show.jsp").forward(req,resp);
        } else
        if (pathInfo.split("/").length!=2){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else
        if ("/find".equals(pathInfo)) {
            if (req.getParameter("entityId") != null) {
                if (!req.getParameter("entityId").isBlank()) {
                    BaseEntity entity = service.read(className, Long.valueOf(req.getParameter("entityId")));
                    req.setAttribute("entity", entity);
                } else if (!req.getParameter("entityName").isBlank()){
                    List<Skill> entities = service.findByName(className,req.getParameter("entityName"));
                    if (!req.getParameter("grade").isBlank()){
                        Skill entity = entities.stream().filter(e -> req.getParameter("grade").equalsIgnoreCase(e.getGrade())).findAny().orElse(null);
                        req.setAttribute("entity", entity);
                    }
                    else
                    req.setAttribute("entities", entities);
                }else {
                    String message = ("Skill not found");
                    req.setAttribute("message", message);
                }
            }
            req.getRequestDispatcher("/view/skill/find.jsp").forward(req, resp);
        } else
        if ("/updateFind".equals(pathInfo)) {
            if (req.getParameter("entityId") != null) {
                BaseEntity entity = service.getEntity(className, req.getParameter("entityId"), "");
                if (entity == null) {
                    String message = ("Skill with input ID not exists");
                    req.setAttribute("message", message);
                } else {
                    req.setAttribute("entity", entity);
                }
            }
            req.getRequestDispatcher("/view/skill/update.jsp").forward(req, resp);
        } else
        if ("/get".equals(pathInfo)){
            Skill entity = (Skill) service.read(className,Long.valueOf(req.getParameter("id")));
            req.setAttribute("entity", entity);
            req.getRequestDispatcher("/view/skill/details.jsp").forward(req, resp);
        } else
        if ("/create".equals(pathInfo)){
            req.getRequestDispatcher("/view/skill/create.jsp").forward(req,resp);
        } else
        if ("/update".equals(pathInfo)){
            if (req.getParameter("entityId") != null) {
                doPut(req, resp);
            }
            req.getRequestDispatcher("/view/skill/update.jsp").forward(req,resp);
        } else
        if ("/delete".equals(pathInfo)){
            if (req.getParameter("entityId") != null) {
                doDelete(req, resp);
            }
            req.getRequestDispatcher("/view/skill/delete.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        req.setCharacterEncoding("UTF-8");
        if ("/create".equals(pathInfo)){
                Skill newEntity = Skill.builder()
                        .name(req.getParameter("name"))
                        .grade(req.getParameter("grade"))
                        .build();
                req.setAttribute("entity", service.save(className, newEntity));
            req.getRequestDispatcher("/view/skill/create.jsp").forward(req,resp);

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        req.setCharacterEncoding("UTF-8");
        if ("/update".equals(pathInfo)){
            Skill entity = (Skill) service.read(className, Long.valueOf(req.getParameter("entityId")));
            entity.setName(req.getParameter("entityName"));
            entity.setGrade(req.getParameter("grade"));
            service.save(className, entity);
            req.setAttribute("message", "Data updated");
        }
        req.getRequestDispatcher("/view/skill/update.jsp").forward(req,resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        req.setCharacterEncoding("UTF-8");
        if ("/delete".equals(pathInfo)){
            Skill entity = (Skill) service.read(className,Long.valueOf(req.getParameter("entityId")));
            req.setAttribute("entity", entity);
            String message = " was deleted";
            req.setAttribute("message", message);
            service.delete(className,Long.valueOf(req.getParameter("entityId")));
            req.getRequestDispatcher("/view/skill/delete.jsp").forward(req,resp);
        }
    }
}
