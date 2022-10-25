package my.first.dao;

import my.first.MysqlSessionFactory;
import org.hibernate.Session;
import my.first.model.Department;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

public class DepartmentDaoImpl implements DepartmentDao {

    private final SessionFactory sessionFactory;

    public DepartmentDaoImpl() {
        this(MysqlSessionFactory.getInstance());
    }

    public DepartmentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Department dep) {
        Transaction trns = null;
        try (Session session = sessionFactory.openSession()) {
            trns = session.beginTransaction();
            session.saveOrUpdate(dep);
            trns.commit();
        } catch (Exception ex) {
            if (trns != null) trns.rollback();
            throw ex;
        }
    }

    @Override
    public Department findById(long id) {
        return sessionFactory.openSession().get(Department.class, id);
    }

    @Override
    public void update(Department dep) {
        create(dep);
    }

    @Override
    public void delete(long id) {
        Department department = sessionFactory.openSession().load(Department.class, id);
        delete(department);
    }

    @Override
    public void delete(Department dep) {
        Transaction trns = null;
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            session.delete(dep);
            trns.commit();
        } catch (Exception ex) {
            if (trns != null) trns.rollback();
            throw ex;
        } finally {
            session.close();
        }
    }
}
