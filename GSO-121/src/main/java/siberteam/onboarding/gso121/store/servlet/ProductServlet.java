package siberteam.onboarding.gso121.store.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import siberteam.onboarding.gso121.store.dao.ProductDao;
import siberteam.onboarding.gso121.store.data.dto.CreateUpdateProductDto;
import siberteam.onboarding.gso121.store.data.dto.ProductDto;
import siberteam.onboarding.gso121.store.data.mapper.ProductMapper;
import siberteam.onboarding.gso121.store.exception.ProductNotFoundException;
import siberteam.onboarding.gso121.store.exception.ValidationException;
import siberteam.onboarding.gso121.store.service.ProductService;
import siberteam.onboarding.gso121.store.validation.ProductRequestValidator;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

@WebServlet(name = "productServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    private ProductRequestValidator validator;
    private ProductService productService;
    private ObjectMapper mapper;

    @Override
    public void init() {
        String url = getServletContext().getInitParameter("db-url");
        Properties props = new Properties();
        props.put("user", getServletContext().getInitParameter("db-user"));
        props.put("password", getServletContext().getInitParameter("db-password"));
        ProductDao productDao = new ProductDao(url, props);
        ProductMapper productMapper = new ProductMapper();
        productService = new ProductService(productDao, productMapper);
        mapper = new ObjectMapper();
        validator = new ProductRequestValidator();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            response.setContentType("application/json");
            Collection<ProductDto> products = productService.getAll();
            mapper.writeValue(response.getWriter(), products);
        } catch (Exception exception) {
            response.getWriter().println("internal server error");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            response.setContentType("application/json");
            CreateUpdateProductDto createUpdateProductDto =
                    mapper.readValue(request.getReader(), CreateUpdateProductDto.class);
            validator.validateDto(createUpdateProductDto);
            productService.create(createUpdateProductDto);
            response.setStatus(201);
        } catch (ValidationException exception) {
            response.getWriter().println(exception.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (IOException exception) {
            response.getWriter().println("Bad request body");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception exception) {
            response.getWriter().println("internal server error");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            validator.validateCodeParam(request);
            response.setContentType("application/json");
            Integer code = Integer.valueOf(request.getParameter("code"));
            CreateUpdateProductDto createUpdateProductDto =
                    mapper.readValue(request.getReader(), CreateUpdateProductDto.class);
            validator.validateDto(createUpdateProductDto);
            productService.update(code, createUpdateProductDto);
        } catch (ValidationException exception) {
            response.getWriter().println(exception.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (IOException exception) {
            response.getWriter().println("Bad request body");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (ProductNotFoundException exception) {
            response.getWriter().println(exception.getMessage());
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception exception) {
            response.getWriter().println("internal server error");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            validator.validateCodeParam(request);
            response.setContentType("application/json");
            Integer code = Integer.valueOf(request.getParameter("code"));
            productService.delete(code);
        } catch (ValidationException exception) {
            response.getWriter().println(exception.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (ProductNotFoundException exception) {
            response.getWriter().println(exception.getMessage());
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception exception) {
            response.getWriter().println("internal server error");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
