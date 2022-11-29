package my.first.dao;

import my.first.model.Counter;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CounterDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public CounterDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(Counter c) {
        sessionFactory.getCurrentSession().saveOrUpdate(c);
    }

    public Counter findById(long id) {
        return sessionFactory.getCurrentSession().get(Counter.class, id);
    }

    public void update(Counter c) {
        create(c);
    }
}
