package by.it.tasks.task_6.model;

import by.it.tasks.task_6.model.base.BaseTest;
import org.junit.Test;

import javax.persistence.EntityManager;

public class ForeignGenTest extends BaseTest {

	@Test
	public void create() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		SimpleClass valueOne = new SimpleClass();
		valueOne.setName("main text");
		em.persist(valueOne);

		ForeignGen item = new ForeignGen();
		item.setName("test");
		item.setSimpleClass(valueOne);
		valueOne.setForeign(item);

		log.info("Before persist");
		em.persist(item);
		var e = em.createQuery("select k From ForeignGen k", ForeignGen.class).getResultList();
		for (var q : e) {
			System.out.println("id = " + q.getId() + ",  name = " + q.getName() +
					"\nid(main key) = " + q.getSimpleClass().getId());
		}
		log.info("After persist");

		em.createQuery("delete from ForeignGen").executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
}