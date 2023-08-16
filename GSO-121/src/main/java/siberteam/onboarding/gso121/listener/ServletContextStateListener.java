package siberteam.onboarding.gso121.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import siberteam.onboarding.gso121.store.dao.ProductDao;
import siberteam.onboarding.gso121.store.data.mapper.ProductMapper;
import siberteam.onboarding.gso121.store.service.ProductService;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Properties;

@WebListener
public class ServletContextStateListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String url = servletContextEvent.getServletContext().getInitParameter("db-url");
        Properties props = new Properties();
        props.put("user", servletContextEvent.getServletContext().getInitParameter("db-user"));
        props.put("password", servletContextEvent.getServletContext().getInitParameter("db-password"));
        ProductDao productDao = new ProductDao(url, props);
        ProductMapper productMapper = new ProductMapper();
        ProductService productService = new ProductService(productDao, productMapper);
        ObjectMapper mapper = new ObjectMapper();
        servletContextEvent.getServletContext().setAttribute("dao", productDao);
        servletContextEvent.getServletContext().setAttribute("productService", productService);
        servletContextEvent.getServletContext().setAttribute("objectMapper", mapper);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ProductDao productDao = (ProductDao) servletContextEvent.getServletContext().getAttribute("dao");
        productDao.closeConnections();
    }
}
