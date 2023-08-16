package siberteam.onboarding.gso121.store.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import siberteam.onboarding.gso121.store.dao.ProductDao;
import siberteam.onboarding.gso121.store.data.mapper.ProductMapper;
import siberteam.onboarding.gso121.store.service.ProductService;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Properties;

@WebListener
public class ServletContextStateListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        String url = context.getInitParameter("db-url");
        Properties props = new Properties();
        props.put("user", context.getInitParameter("db-user"));
        props.put("password", context.getInitParameter("db-password"));
        ProductDao productDao = new ProductDao(url, props);
        ProductMapper productMapper = new ProductMapper();
        ProductService productService = new ProductService(productDao, productMapper);
        ObjectMapper mapper = new ObjectMapper();
        context.setAttribute("dao", productDao);
        context.setAttribute("productService", productService);
        context.setAttribute("objectMapper", mapper);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ProductDao productDao = (ProductDao) servletContextEvent.getServletContext().getAttribute("dao");
        productDao.closeConnections();
    }
}
