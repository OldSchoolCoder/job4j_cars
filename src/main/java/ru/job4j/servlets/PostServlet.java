package ru.job4j.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.model.Post;
import ru.job4j.model.User;
import ru.job4j.store.AdRepository;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(PostServlet
            .class.getName());

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        AdRepository store = new AdRepository();
        Optional<Post> postOptional = store.findPostById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            ObjectMapper mapper = new ObjectMapper();
            String postAsString = mapper.writeValueAsString(post);
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("json");
            resp.getWriter().write(postAsString);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error!" +
                    " Post not find!");
        }
    }
}
