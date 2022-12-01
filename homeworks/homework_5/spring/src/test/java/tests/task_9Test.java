package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import tasks.task_9.beans.Person;
import tasks.task_9.beans.Parrot;
import tasks.task_9.config.ProjectConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ProjectConfig.class })
public class task_9Test {

	@Autowired
	private ApplicationContext context;

	@Test
	public void testGoshaIsInTheSpringContext() {
		Parrot p = context.getBean("parrotGosha", Parrot.class);

		assertEquals("Gosha", p.getName());
	}

	@Test
	public void testKokoIsInTheSpringContext() {
		Parrot p = context.getBean("parrot", Parrot.class);

		assertEquals("Koko", p.getName());
	}

	@Test
	public void testIvanIsInTheSpringContext() {
		Person p = context.getBean("person", Person.class);

		assertEquals("Ivan", p.getName());
	}

	@Test
	public void testIvanOwnsGosha() {
		Person p = context.getBean("person", Person.class);

		assertNotNull(p.getParrot());
		assertEquals("Gosha", p.getParrot().getName());
	}
}
