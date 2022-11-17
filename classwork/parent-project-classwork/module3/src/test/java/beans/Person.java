package beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContextAware;

import java.net.http.HttpClient;
import java.util.Map;

@Getter
@Setter
public class Person implements InitializingBean, DisposableBean, ApplicationContextAware {

    private String name;

    private String secondName;

    private AbstractAddress homeAddress;

    private Map<Integer, Person> children;

    public Person() {
        System.out.println("Call constructor.");
    }

    public void init() {
        System.out.println("Call init. ");
    }

    public void destroy() {
        System.out.println("Call destroy.");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Call afterProperties.");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Call setApplicationContext");
    }
}
