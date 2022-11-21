package by.it.tasks.task_6.model.base;

import by.it.tasks.task_6.model.HILOGenTest;
import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.logging.Logger;

public class BaseTest {

	public EntityManagerFactory emf;

	public Logger log = Logger.getLogger(HILOGenTest.class.getName());

	@Before
	public void init() {
		emf = Persistence.createEntityManagerFactory("by.it.model");
	}

	@After
	public void close() {
		emf.close();
	}
}
