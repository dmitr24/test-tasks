package siberteam.onboarding.gso121.files;

import javax.servlet.RequestDispatcher;
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
    private static final String UPLOAD_DIR = "/var/tmp/files/";

    @Override
    public void init() {
        Path mainDirectoryPath = Paths.get(UPLOAD_DIR);
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
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("result.jsp");
        Part filePart = request.getPart("file");
        if (filePart == null) {
            request.setAttribute("result", "File is required for submission");
            requestDispatcher.forward(request, response);
            return;
        }
        if (filePart.getSize() == 0) {
            request.setAttribute("result", "Empty files not allowed");
            requestDispatcher.forward(request, response);
            return;
        }
        String fileName = filePart.getSubmittedFileName();
        if (Files.exists(Paths.get(UPLOAD_DIR + fileName))) {
            request.setAttribute("result", "File with such name already exists");
            requestDispatcher.forward(request, response);
            return;
        }
        filePart.write(fileName);
        request.setAttribute("result", "File uploaded successfully");
        requestDispatcher.forward(request, response);
    }
}
