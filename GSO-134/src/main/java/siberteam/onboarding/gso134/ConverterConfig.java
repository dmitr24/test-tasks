package siberteam.onboarding.gso134;

import org.apache.commons.beanutils.BeanUtilsBean;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ConverterConfig implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        BeanUtilsBean.getInstance().getConvertUtils().register(false, true, 0);
    }
}
