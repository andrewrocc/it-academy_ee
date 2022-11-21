package by.it.tasks.task_7;

import by.it.tasks.task_7.model.Address;
import by.it.tasks.task_7.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//I do not know what to test here
public class ApplicationRunner {

	private static EntityManager em;

	public static void main(String[] args) {
		createEntityManager();
		Person p = new Person(null, 25, "egor", "pupkin",
				new Address("pyskina", "minsk", "200000"));
		em.persist(p);
		printEntityMembers();
		deleteDataAndCloseEntity();
	}

	public static void createEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("by.it.model");
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}

	public static void printEntityMembers() {
		var selectResult = em.createQuery("select k From Person k", Person.class).getResultList();
		for (var element : selectResult) {
			System.out.println(element);
		}
	}

	public static void deleteDataAndCloseEntity() {
		em.createQuery("delete from Person").executeUpdate();
		em.getTransaction().commit();
		em.clear();
		em.getEntityManagerFactory().close();
		em.close();
	}
}
