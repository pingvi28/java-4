package ru.kpfu.itis.kashapova.framework;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.kpfu.itis.kashapova.app.Config;
import ru.kpfu.itis.kashapova.exeption.WebFrameworkException;
import ru.kpfu.itis.kashapova.framework.controllers.Controller;
import ru.kpfu.itis.kashapova.framework.mapper.RequestMapper;
import ru.kpfu.itis.kashapova.framework.mav.ModelAndView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/")
// точка входа
public class MainServlet extends HttpServlet {

    private final String VIEW_PREFIX = "/WEB-INF/jsp/";
    private final String VIEW_SUFFIX = ".jsp";

    private RequestMapper mapper;
    private ApplicationContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        context = new AnnotationConfigApplicationContext(Config.class);
        try {
            mapper = context.getBean(RequestMapper.class);
        }
        catch(NoSuchBeanDefinitionException ex){
            throw new WebFrameworkException("Bean RequestMapper has not be configured.", ex);
        }
    }

    //маршрутизация
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI();
        // убираем контекст
        String cleanRequestPath = requestUri.substring(req.getContextPath().length());

        if (mapper.hasRoute(cleanRequestPath)) {
            Controller controller = (Controller) context.getBean(mapper.getRoute(cleanRequestPath));
            ModelAndView response = controller.doAction(req);
            for(Map.Entry<String, Object> entry : response.getModel().entrySet()) {
                req.setAttribute(entry.getKey(), entry.getValue());
            }
            this.getServletContext().getRequestDispatcher(resolveView(response.getViewName())).forward(req, resp);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().println("404: Resource not found, try again");
        }
    }

    protected String resolveView(String viewName) {
        return VIEW_PREFIX + viewName + VIEW_SUFFIX;
    }
}
