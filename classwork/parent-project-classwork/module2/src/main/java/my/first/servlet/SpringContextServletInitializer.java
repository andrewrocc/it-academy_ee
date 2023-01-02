package my.first.servlet;

import my.first.DataConfig;
import my.first.security.WebSecurityConfig;
import my.first.service.ServiceContextConfiguration;
import my.first.web.WebConfiguration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

public class SpringContextServletInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfiguration.class, DataConfig.class,
                ServiceContextConfiguration.class, WebSecurityConfig.class);
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);

        final ServletRegistration.Dynamic registration = ctx.addServlet("dispatcherServlet", dispatcherServlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("*.html");
        registration.addMapping("*.jpg");
    }
}
