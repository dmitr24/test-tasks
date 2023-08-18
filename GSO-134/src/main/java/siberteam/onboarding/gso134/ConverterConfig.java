package siberteam.onboarding.gso134;

import org.apache.commons.beanutils.BeanUtilsBean;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ConverterConfig implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String logConfigPath = getClass().getClassLoader().getResource("log4j2.xml").getPath();
        System.setProperty("log4j.configurationFile", logConfigPath);
        BeanUtilsBean.getInstance().getConvertUtils().register(false, true, 0);
    }
}
