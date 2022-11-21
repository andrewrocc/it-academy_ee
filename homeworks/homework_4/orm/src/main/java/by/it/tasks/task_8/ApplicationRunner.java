package by.it.tasks.task_8;

import by.it.tasks.task_8.model.perClass.EmployeePerClass;
import by.it.tasks.task_8.model.perClass.PersonPerClass;
import by.it.tasks.task_8.model.perClass.StudentPerClass;
import by.it.tasks.task_8.model.perSubclass.EmployeeSubclass;
import by.it.tasks.task_8.model.oneTable.PersonOneTable;
import by.it.tasks.task_8.model.perSubclass.PersonSubclass;
import by.it.tasks.task_8.model.perSubclass.StudentSubclass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//I do not know what to test here, too sry
public class ApplicationRunner {

	private static EntityManager em;

	public static void main(String[] args) {
		createEntityManager();

		createOneTable();
		createSubClass();
		createPerClass();

		clearAndCloseEntity();
	}

	private static void createPerClass() {
		PersonPerClass p = new PersonPerClass(null, "david", "villa");
		EmployeePerClass e = new EmployeePerClass("best software", 123.3);
		StudentPerClass s = new StudentPerClass("vmsis", 8.8);
		e.setName("mark");
		e.setSurname("baum");
		s.setName("nameless");
		s.setSurname("test");
		em.getTransaction().begin();
		em.persist(p);
		em.persist(e);
		em.persist(s);
		em.getTransaction().commit();
		System.out.println("----------InheritanceType table per class----------");
		printEntityMembers(PersonPerClass.class);
		deleteDataFromTable(PersonPerClass.class.getSimpleName());
		System.out.println("----------InheritanceType table per class----------");
	}

	private static void createSubClass() {
		PersonSubclass p = new PersonSubclass(null, "valera", "molod");
		StudentSubclass s = new StudentSubclass("slovo", 3.2);
		EmployeeSubclass e = new EmployeeSubclass("ooo 3 cores", 8.3);
		em.getTransaction().begin();
		em.persist(p);
		em.persist(s);
		em.persist(e);
		em.getTransaction().commit();
		System.out.println("----------InheritanceType table subclass----------");
		printEntityMembers(PersonSubclass.class);
		deleteDataFromTable(PersonSubclass.class.getSimpleName());
		System.out.println("----------InheritanceType table subclass----------");
	}

	private static void createOneTable() {
		PersonOneTable p = new PersonOneTable(null, 'M', "nameless", "none",
				"VMSIS", 32.4, "BOMJ", -3.2);
		em.persist(p);
		em.getTransaction().commit();
		System.out.println("----------InheritanceType table one class----------");
		printEntityMembers(PersonOneTable.class);
		deleteDataFromTable(PersonOneTable.class.getSimpleName());
		System.out.println("----------InheritanceType table one class----------");
	}

	private static void createEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("by.it.model");
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}

	public static void printEntityMembers(Class<?> namelessClass) {
		String className = namelessClass.getSimpleName();
		var selectResult = em.createQuery(String.format("select k From %s k", className), namelessClass).getResultList();
		for (var listElement : selectResult) {
			System.out.println(listElement);
		}
	}

	private static void deleteDataFromTable(String tableName) {
		em.getTransaction().begin();
		em.createQuery(String.format("delete from %s", tableName)).executeUpdate();
		em.getTransaction().commit();
		em.clear();
	}

	private static void clearAndCloseEntity() {
		em.clear();
		em.getEntityManagerFactory().close();
		em.close();
	}
}
