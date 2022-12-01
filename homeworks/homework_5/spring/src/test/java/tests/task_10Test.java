package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import tasks.task_10.beans.Elephant;
import tasks.task_10.annotations.Device;
import tasks.task_10.config.ProjectConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ProjectConfig.class })
public class task_10Test {

	@Autowired
	private ApplicationContext context;

	@Test
	public void testAnimalIsInTheSpringContext() {
		Elephant e = context.getBean(Elephant.class);

		assertNotNull(e);
		assertEquals("karl", e.getName());
	}

	@Test
	public void testDeviceIsNotInTheSpringContext() {
		List<String> beans = Arrays.stream(context.getBeanDefinitionNames())
				.filter(bean -> !bean.contains("org.springframework") && !bean.contains("Device"))
				.toList();
		assertFalse(beans.contains(Device.class.getSimpleName()));
	}
}
