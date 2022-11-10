package beans;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PersonTest {

    @Test
    public void testCreatePerson() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Person p = context.getBean("person", Person.class);
        Person p1 = context.getBean("person", Person.class);
        assertEquals("Yulia", p.getName());
        assertEquals(p.hashCode(), p1.hashCode());

        assertNotNull(p1.getHomeAddress());
        assertEquals(3, p1.getHomeAddress().getPhoneNumbers().size());
        assertEquals(2, p1.getChildren().size());
        context.close();
    }
}
