package my.first.service;

import my.first.dao.EmployeeDao;
import my.first.model.Employee;
import my.first.model.EmployeeDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Transactional
    public void add(Employee e) {
        EmployeeDetail employeeDetail = e.getEmployeeDetail();
        if (employeeDetail.getEmployee() == null) {
            e.getEmployeeDetail().setEmployee(e);
        }
        employeeDao.create(e);
    }
}
