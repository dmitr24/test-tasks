package siberteam.onboarding.gso121.name;

import java.io.*;
import java.util.Objects;
import javax.servlet.http.*;

public class NameServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        Object name = request.getSession().getAttribute("name");
        if (name == null) {
            out.println("I don't know your name");
        } else {
            out.println("Hello, " + name + "!");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String newName = request.getParameter("name");
        if (Objects.nonNull(newName) && newName.trim().length() > 0) {
            request.getSession().setAttribute("name", request.getParameter("name"));
        } else {
            PrintWriter out = response.getWriter();
            out.println("Name can't be null or empty");
            response.setStatus(400);
        }
    }
}
