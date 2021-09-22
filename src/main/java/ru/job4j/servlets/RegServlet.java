package ru.job4j.servlets;

import ru.job4j.model.User;
import ru.job4j.store.AdRepository;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(RegServlet
            .class.getName());

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        AdRepository store = new AdRepository();
        String email = req.getParameter("email");
        Optional<User> userOptional = store.findByEmail(email);
        if (userOptional.isEmpty()) {
            int id = 0;
            User user = new User();
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            user.setPassword(password);
            user.setEmail(email);
            user.setName(username);
            user.setId(id);
            store.add(user);
            req.getRequestDispatcher("success.html").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    " Registration Error! User already exists! ");
        }
    }
}
