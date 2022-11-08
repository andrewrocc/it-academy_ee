package by.it.tasks.task_2.loader;

import by.it.pojos.Person;
import by.it.util.HibernateUtil;

import javax.persistence.EntityManager;

public class PersonLoader {

    public static void main(String[] args) {
        Person vovaSl = new Person(null, 35, "Vova", "Slabko");
        Person donaldTrump = new Person(null, 100, "Donald", "Trump");
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(vovaSl);
        em.persist(donaldTrump);
        em.getTransaction().commit();
        HibernateUtil.close();
    }
}
