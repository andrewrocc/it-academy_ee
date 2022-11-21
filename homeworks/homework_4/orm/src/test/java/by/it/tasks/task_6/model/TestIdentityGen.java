package by.it.tasks.task_6.model;

import by.it.tasks.task_6.model.base.BaseTest;
import org.junit.Test;

import javax.persistence.EntityManager;

public class TestIdentityGen extends BaseTest {

	@Test
	public void testIdentityStrategy() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		NativeGen keyboard = new NativeGen();
		keyboard.setName("test");

		log.info("Before persist");
		em.persist(keyboard);
		var e = em.createQuery("select k From NativeGen k", NativeGen.class).getResultList();
		for (var q : e) {
			System.out.println(q);
		}
		log.info("After persist");

		em.createQuery("delete from NativeGen").executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
}
