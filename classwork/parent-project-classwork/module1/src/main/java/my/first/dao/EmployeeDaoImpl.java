package my.first.dao;

import org.hibernate.Session;
import my.first.model.Employee;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private SessionFactory sessionFactory;

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
            Employee loadedEmployee = sess.load(Employee.class, em.getId());
            sess.delete(loadedEmployee);
            tr.commit();
        } catch (Exception ex) {
            if (tr != null) tr.rollback();
            throw ex;
        }
    }
}
