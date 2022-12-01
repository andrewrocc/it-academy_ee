package tests;

import org.junit.Test;
import tasks.task_2.beans.Car;
import org.junit.runner.RunWith;
import tasks.task_2.beans.Owner;
import tasks.task_2.config.ProjectConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ProjectConfig.class })
public class task_2Test {

	@Autowired
	private ApplicationContext context;

	@Test
	public void testCarHasTheBrandAudi() {
		Car c = context.getBean(Car.class);
		assertEquals("Audi", c.getBrand());
	}

	@Test
	public void testOwnerHasTheCar() {
		Owner o = context.getBean(Owner.class);
		assertNull(o.getName());
	}
}
