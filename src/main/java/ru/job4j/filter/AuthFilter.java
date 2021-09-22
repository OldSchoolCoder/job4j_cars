package ru.job4j.filter;

import ru.job4j.model.Post;
import ru.job4j.model.User;
import ru.job4j.store.AdRepository;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(AuthFilter
            .class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest sreq, ServletResponse
            sresp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) sreq;
        HttpServletResponse resp = (HttpServletResponse) sresp;
        String uri = req.getRequestURI();
        if (uri.endsWith("error.jsp")) {
            chain.doFilter(sreq, sresp);
            return;
        }
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher("login.html").forward(req, resp);
            chain.doFilter(sreq, sresp);
            return;
        } else {
            String strId = req.getParameter("id");
            if (strId == null) {
                chain.doFilter(sreq, sresp);
                return;
            }
            Integer id = Integer.valueOf(strId);
            if (isOwner(id, user)) {
                chain.doFilter(sreq, sresp);
                return;
            }
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Not owner!" +
                    " Access denied!");
        }
        chain.doFilter(sreq, sresp);
    }

    private boolean isOwner(Integer id, User user) {
        AdRepository store = new AdRepository();
        Optional<Post> postOptional = store.findPostById(id);
        User author = postOptional.get().getAuthor();
        return user.getName().equals(author.getName());
    }

    @Override
    public void destroy() {
    }
}
