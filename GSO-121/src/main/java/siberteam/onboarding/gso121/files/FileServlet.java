package siberteam.onboarding.gso121.files;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(name = "fileServlet", value = "/fileServlet")
@MultipartConfig(
        location = "/var/tmp/files",
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 15,
        fileSizeThreshold = 1024 * 1024 * 2
)
public class FileServlet extends HttpServlet {
    private final String uploadDir = "/var/tmp/files/";

    @Override
    public void init() {
        Path mainDirectoryPath = Paths.get(uploadDir);
        if (!Files.exists(mainDirectoryPath)) {
            try {
                Files.createDirectory(mainDirectoryPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        Path filePath = Paths.get(uploadDir + fileName);
        if (Files.exists(filePath)) {
            PrintWriter out = response.getWriter();
            out.println("File with such name already exists");
            response.setStatus(400);
        } else {
            filePart.write(fileName);
            response.setStatus(201);
        }
    }
}
