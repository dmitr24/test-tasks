package siberteam.onboarding.gso133;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import java.io.File;

public class ProductApplication {
    public static void main(String[] args) throws LifecycleException {
        String webAppDirLocation = "GSO-133/src/main/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        System.out.println(tomcat.getServer().getPort());
        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webAppDirLocation).getAbsolutePath());
        tomcat.start();
        System.out.println(tomcat.getServer().getState());
        tomcat.getServer().await();
    }
}
