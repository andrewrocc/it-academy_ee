package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import tasks.task_8.beans.Parrot;
import tasks.task_8.beans.Person;
import tasks.task_8.config.ProjectConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ProjectConfig.class })
public class task_8Test {

	@Autowired
	private ApplicationContext context;

	@Test
	public void testGogaIsInTheSpringContext() {
		Parrot p = context.getBean(Parrot.class);

		assertEquals("goga", p.getName());
	}

	@Test
	public void testValeraIsInTheSpringContext() {
		Person p = context.getBean("person", Person.class);

		assertEquals("valera", p.getName());
	}

	@Test
	public void testMarkIsInTheSpringContext() {
		Person m = context.getBean("personMark", Person.class);

		assertEquals("mark", m.getName());
	}

	@Test
	public void testValeraOwnsParrot() {
		Person p = context.getBean("person", Person.class);

		assertNotNull(p.getParrot());
		assertEquals("goga", p.getParrot().getName());
	}

	@Test
	public void testMarkOwnsParrot() {
		Person m = context.getBean("personMark", Person.class);

		assertNotNull(m.getParrot());
		assertEquals("goga", m.getParrot().getName());
	}
}
