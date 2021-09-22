package ru.job4j.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ExceptionHandler extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException,
            IOException {
        processError(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException,
            IOException {
        processError(request, response);
    }

    private void processError(HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
            ServletException {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        String errorMessage = (String) request
                .getAttribute("javax.servlet.error.message");
        HttpSession session = request.getSession();
        session.setAttribute("statusCode", statusCode);
        session.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
}
