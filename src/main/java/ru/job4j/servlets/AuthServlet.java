package ru.job4j.servlets;

import ru.job4j.model.User;
import ru.job4j.store.AdRepository;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

public class AuthServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AuthServlet
            .class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        AdRepository store = new AdRepository();
        Optional<User> userOptional = store.findByEmail(email);
        if (userOptional.isPresent() && userOptional.get().getPassword()
                .equals(password)) {
            User user = userOptional.get();
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            req.getRequestDispatcher("index.html").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error!" +
                    " Bed email or password!");
        }
    }
}
