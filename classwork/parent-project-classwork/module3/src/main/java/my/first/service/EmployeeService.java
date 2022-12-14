package my.first.service;

import my.first.dao.EmployeeDao;
import my.first.model.Employee;
import my.first.model.EmployeeDetail;
import my.first.model.EmployeePhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Transactional
    public void add(Employee e, byte[] photo) {
        EmployeeDetail employeeDetail = e.getEmployeeDetail();
        if (employeeDetail.getEmployee() == null) {
            e.getEmployeeDetail().setEmployee(e);
        }
        if (e.getEmployeePhoto() == null) {
            EmployeePhoto employeePhoto = new EmployeePhoto();
            employeePhoto.setPhoto(photo);
            employeePhoto.setEmployee(e);
            e.setEmployeePhoto(employeePhoto);
        }

        employeeDao.create(e);
    }

    public List<Employee> getAll() {
        return employeeDao.findAll();
    }

    public Employee getById(long id) {
        return employeeDao.findById(id);
    }
}
