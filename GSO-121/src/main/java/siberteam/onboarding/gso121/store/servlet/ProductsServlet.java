package siberteam.onboarding.gso121.store.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import siberteam.onboarding.gso121.store.data.dto.ProductDto;
import siberteam.onboarding.gso121.store.service.ProductService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "productsServlet", value = "/products")
public class ProductsServlet extends HttpServlet {
    private ProductService productService;
    private ObjectMapper mapper;

    @Override
    public void init() {
        this.productService = (ProductService) getServletContext().getAttribute("productService");
        this.mapper = (ObjectMapper) getServletContext().getAttribute("objectMapper");
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
}
