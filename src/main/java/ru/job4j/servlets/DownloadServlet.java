package ru.job4j.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DownloadServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(DownloadServlet
            .class.getName());

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException,
            IOException {
        String name = req.getParameter("name");
        resp.setContentType("name=" + name);
        resp.setContentType("image/png");
        resp.setHeader("Content-Disposition", "attachment; " +
                "filename=\"" + name + "\"");
        File file = new File("images//photo" + File.separator + name);
        try (FileInputStream in = new FileInputStream(file)) {
            resp.getOutputStream().write(in.readAllBytes());
        }
    }
}
