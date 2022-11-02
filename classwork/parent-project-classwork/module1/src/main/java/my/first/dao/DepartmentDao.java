package my.first.dao;

import my.first.model.Department;

import java.util.List;

public interface DepartmentDao {
    
    void create(Department dep);

    Department findById(long id);

    List<String> findByAllDepartmentNames();

    void update(Department dep);

    void delete(long id);

    void delete(Department dep);
}
