package siberteam.onboarding.gso121.files;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(name = "fileServlet", value = "/fileServlet")
@MultipartConfig(
        location = "/var/tmp/files",
        maxFileSize = 1024 * 1024,
        maxRequestSize = 1024 * 1024,
        fileSizeThreshold = 1024 * 128
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
        if (filePart == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "File is required for submission");
            return;
        }
        if (filePart.getSize() == 0) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Empty files not allowed");
            return;
        }
        String fileName = filePart.getSubmittedFileName();
        if (Files.exists(Paths.get(uploadDir + fileName))) {
            response.sendError(HttpServletResponse.SC_CONFLICT, "File with such name already exists");
            return;
        }
        filePart.write(fileName);
        response.setStatus(201);
    }
}
