package ru.job4j.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(HelloServlet
            .class.getName());

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        ObjectMapper mapper = new ObjectMapper();
        String userAsString = mapper.writeValueAsString(user);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(userAsString);
    }
}
