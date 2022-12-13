package my.first.web;

import my.first.model.Employee;
import my.first.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static java.time.LocalDateTime.now;

@Controller
public class AddEmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/add-employee.html")
    public String getAddEmployeePage() {
        return "add_employee";
    }

    @PostMapping("/add-employee.html")
    public String addEmployee(Employee e) {
        System.out.println("Call add employee detail " + now() + "\n" + e);
        employeeService.add(e);
        return "redirect:/employee-list.html";
    }
}
