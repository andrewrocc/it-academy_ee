package my.first.dao.impl;

import my.first.dao.DepartmentDao;
import my.first.model.Department;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class DepartmentDaoImpl implements DepartmentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Department dep) {
        sessionFactory.getCurrentSession().saveOrUpdate(dep);
    }

    @Override
    public Department findById(long id) {
        return sessionFactory.getCurrentSession().get(Department.class, id);
    }

    @Override
    public void update(Department dep) {
        create(dep);
    }

    @Override
    @Transactional(transactionManager = "transactionManager")
    public void delete(long id) {
        sessionFactory.getCurrentSession().createQuery("delete from Department where id=" + id).executeUpdate();
    }

    @Override
    public void delete(Department dep) {
        sessionFactory.getCurrentSession().refresh(dep);
        sessionFactory.getCurrentSession().delete(dep);
    }

    @Override
    public List<Department> findAll() {
        String query = "FROM Department";
        return sessionFactory.getCurrentSession().createQuery(query, Department.class).list();
    }

    @Override
    public List<String> findAllDepartmentNames() {
        String query = "SELECT d.departmentName FROM Department AS d";
        return sessionFactory.getCurrentSession().createQuery(query, String.class).list();
    }
}
