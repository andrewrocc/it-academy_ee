package by.it.tasks.task_6.model;

import by.it.tasks.task_6.model.base.BaseTest;
import org.junit.Test;

import javax.persistence.EntityManager;

public class UUIDGenTest extends BaseTest {

	@Test
	public void create() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		UUIDGen item = new UUIDGen();
		item.setName("test");

		log.info("Before persist");
		em.persist(item);
		var e = em.createQuery("select k From UUIDGen k", UUIDGen.class).getResultList();
		for (var q : e) {
			System.out.println(q);
		}
		log.info("After persist");

		em.createQuery("delete from UUIDGen").executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
}