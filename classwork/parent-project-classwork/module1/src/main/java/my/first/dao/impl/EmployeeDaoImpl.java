package my.first.dao.impl;

import my.first.dao.EmployeeDao;
import my.first.model.Employee;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Employee em) {
        sessionFactory.getCurrentSession().saveOrUpdate(em);
    }

    @Override
    public Employee findById(long id) {
        return sessionFactory.getCurrentSession().get(Employee.class, id);
    }

    @Override
    public void update(Employee em) {
        create(em);
    }

    @Override
    public void delete(Employee em) {
        Employee loadedEmployee = sessionFactory.getCurrentSession().load(Employee.class, em.getId());
        sessionFactory.getCurrentSession().delete(loadedEmployee);
    }

    @Override
    public List<Employee> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Employee", Employee.class).list();
    }
}
