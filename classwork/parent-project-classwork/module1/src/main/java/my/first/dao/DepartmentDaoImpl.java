package my.first.dao;

import my.first.model.Department;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class DepartmentDaoImpl implements DepartmentDao {

    //    @Autowired
    private final SessionFactory sessionFactory;

    @Autowired
    public DepartmentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Department dep) {
        sessionFactory.getCurrentSession().saveOrUpdate(dep);
    }

    @Override
    public Department findById(long id) {
        return sessionFactory.getCurrentSession().get(Department.class, id);
    }

    @Override
    public List<String> findByAllDepartmentNames() {
        String hql_query = "SELECT d.departmentName FROM Department AS d";
        return sessionFactory.getCurrentSession().createQuery(hql_query, String.class).list();
    }

    @Override
    public void update(Department dep) {
        create(dep);
    }

    @Override
    public void delete(long id) {
        sessionFactory.getCurrentSession().createQuery("delete from Department where id=" + id).executeUpdate();
    }

    @Override
    public void delete(Department dep) {
        sessionFactory.getCurrentSession().refresh(dep);
        sessionFactory.getCurrentSession().delete(dep);
    }
}
