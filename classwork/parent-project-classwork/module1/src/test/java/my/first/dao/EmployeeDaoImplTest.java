package my.first.dao;

import my.first.model.Department;
import my.first.model.Meeting;
import org.junit.*;
import lombok.SneakyThrows;
import my.first.model.Employee;
import org.dbunit.dataset.IDataSet;
import my.first.model.EmployeeDetail;
import org.hibernate.boot.Metadata;
import my.first.MysqlJdbcDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.*;

public class EmployeeDaoImplTest extends BaseDaoTest {

    EmployeeDaoImpl targetObject;

    @Before
    public void setUp() {
        targetObject = new EmployeeDaoImpl(testSessionFactory);
    }

    @After
    public void tearDown() {
        targetObject = null;
    }

    @Test
    @SneakyThrows
    public void create() {
        //Given
        Connection conn = testMysqlJdbcDataSource.getConnection();

        IDataSet departmentDataSet = new FlatXmlDataSetBuilder()
                .build(EmployeeDaoImplTest.class.getResourceAsStream("DepartmentDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, departmentDataSet);

        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_employeedetail;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);

        Employee employee = new Employee();
        employee.setFirstName("Sasha");

        EmployeeDetail employeeDetail = new EmployeeDetail();
        employeeDetail.setCountry("BELARUS");

        Department department = new Department();
        department.setId(1);
        department.setDepartmentName("TestName");

        employee.setEmployeeDetail(employeeDetail);
        employeeDetail.setEmployee(employee);
        employee.setDepartment(department);

        //When
        targetObject.create(employee);

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from t_employeedetail;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        conn.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
        conn.createStatement().executeUpdate("truncate table t_employeedetail;");
        conn.createStatement().executeUpdate("truncate table t_employee;");
        conn.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
        conn.close();

        DatabaseOperation.DELETE.execute(iDatabaseConnection, departmentDataSet);
    }

    @Test
    @SneakyThrows
    public void findById() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(EmployeeDaoImplTest.class.getResourceAsStream("EmployeeDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        Employee employee = targetObject.findById(101);

        //Then
        assertEquals("+375296101011", employee.getCellPhone());
        assertEquals("Ivan", employee.getFirstName());
        assertEquals("Petrov", employee.getLastName());
        assertEquals("1977-05-24", employee.getBirthDate().toString());
        assertEquals("MINSK", employee.getEmployeeDetail().getCity());

        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }


    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(EmployeeDaoImplTest.class.getResourceAsStream("EmployeeDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        Employee employee = targetObject.findById(101);
        assertNotNull(employee);
        targetObject.delete(employee);

        //Then
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_employeedetail;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(0, actualSize);
    }
}