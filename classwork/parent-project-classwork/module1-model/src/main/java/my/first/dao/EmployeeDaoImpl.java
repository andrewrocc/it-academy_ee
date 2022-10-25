package my.first.dao;

import org.hibernate.Session;
import my.first.model.Employee;
import org.hibernate.Transaction;
import my.first.MysqlSessionFactory;
import org.hibernate.SessionFactory;

public class EmployeeDaoImpl implements EmployeeDao {

    private final SessionFactory sessionFactory;

    public EmployeeDaoImpl() {
        this(MysqlSessionFactory.getInstance());
    }

    public EmployeeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void create(Employee em) {
        Transaction tr = null;
        try (Session sess = sessionFactory.openSession()) {
            tr = sess.beginTransaction();
            sess.saveOrUpdate(em);
            tr.commit();
        } catch (Exception ex) {
            if (tr != null) tr.rollback();
            throw ex;
        }
    }

    @Override
    public Employee findById(long id) {
        return sessionFactory.openSession().get(Employee.class, id);
    }

    @Override
    public void update(Employee em) {
        create(em);
    }

    @Override
    public void delete(Employee em) {
        Transaction tr = null;
        try (Session sess = sessionFactory.openSession()) {
            tr = sess.beginTransaction();
            sess.delete(em);
            tr.commit();
        } catch (Exception ex) {
            if (tr != null) tr.rollback();
            throw ex;
        }
    }
}
