package ru.job4j.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.model.Post;
import ru.job4j.store.AdRepository;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BuildServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(BuildServlet
            .class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = req.getParameter("data");
        String filterType = req.getParameter("filterType");
        AdRepository store = new AdRepository();
        List<Post> posts;
        if (filterType == null || filterType == "") {
            posts = store.allPosts();
        } else if (filterType.equals("power")) {
            posts = store.powerFilter(Integer.valueOf(data));
        } else {
            posts = store.postsFilter(data, filterType);
        }
        ObjectMapper mapper = new ObjectMapper();
        String postsAsString = mapper.writeValueAsString(posts);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(postsAsString);
    }
}
