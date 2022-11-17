package beans;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class PersonServiceAnnotationTest {

    @Test
    public void testSpringAnnotation() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PersonService.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(System.out::println);
        PersonService person = context.getBean(PersonService.class);
        PersonService person1 = context.getBean(PersonService.class);
        System.out.println(person);
        System.out.println(person1);
    }
}
