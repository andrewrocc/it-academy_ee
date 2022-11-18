package my.first.dao;

import org.hibernate.Session;
import my.first.model.Department;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

    @Autowired
    private SessionFactory sessionFactory;

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
    public List<String> findByAllDepartmentNames() {
        try (Session session = sessionFactory.openSession()) {
            String hql_query = "SELECT d.departmentName FROM Department AS d";
            return session.createQuery(hql_query, String.class).list();
        }
    }

    @Override
    public void update(Department dep) {
        create(dep);
    }

    @Override
    public void delete(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("delete from Department where id=" + id).executeUpdate();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) transaction.rollback();
            throw ex;
        }
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
