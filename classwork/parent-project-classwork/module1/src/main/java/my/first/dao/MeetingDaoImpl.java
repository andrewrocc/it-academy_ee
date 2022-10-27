package my.first.dao;

import my.first.MysqlSessionFactory;
import my.first.model.Meeting;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class MeetingDaoImpl implements MeetingDao {

    private final SessionFactory sessionFactory;

    public MeetingDaoImpl() {
        sessionFactory = MysqlSessionFactory.getInstance();
    }

    public MeetingDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Meeting met) {

    }

    @Override
    public List<Meeting> findAll() {
        Session session = sessionFactory.openSession();
        List<Meeting> meetings = session.createQuery("from Meeting", Meeting.class).list();
        session.close();
        return meetings;
    }

    @Override
    public void update(Meeting met) {

    }

    @Override
    public void delete(Meeting met) {

    }
}
