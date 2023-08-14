package siberteam.onboarding.gso121.name;

import java.io.*;
import java.util.Objects;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "nameServlet", value = "/name")
public class NameServlet extends HttpServlet {
    private String nameMessage;

    @Override
    public void init() {
        nameMessage = "I don't know your name";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println(nameMessage);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String newName = request.getParameter("name");
        if (Objects.nonNull(newName) && newName.trim().length() > 0) {
            this.nameMessage = "Hello, " + request.getParameter("name") + "!";
        } else {
            PrintWriter out = response.getWriter();
            out.println("Name can't be null or empty");
            response.setStatus(400);
        }
    }
}
