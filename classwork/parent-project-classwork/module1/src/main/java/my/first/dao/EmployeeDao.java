package my.first.dao;

import my.first.model.Employee;

import java.util.List;

public interface EmployeeDao {

    void create(Employee em);

    Employee findById(long id);

    void update(Employee em);

    void delete(Employee em);

    List<Employee> findAll();
}
