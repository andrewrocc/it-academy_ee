package my.first.dao;

import my.first.model.Department;
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

public class EmployeeDaoImplTest {

    // JDBC data source
    static MysqlJdbcDataSource testMysqlJdbcDataSource;

    // DBUnit connection
    static IDatabaseConnection iDatabaseConnection;

    // Hibernate session factory
    static SessionFactory testSessionFactory;

    EmployeeDaoImpl targetObject;

    @BeforeClass
    @SneakyThrows
    public static void init() {
        testMysqlJdbcDataSource = new MysqlJdbcDataSource("eshop_test.jdbc.properties");
        iDatabaseConnection = new MySqlConnection(testMysqlJdbcDataSource.getConnection(), "eshop_test");

        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate_test.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources( standardRegistry )
                .addAnnotatedClass( Employee.class )
                .addAnnotatedClass( EmployeeDetail.class )
                .addAnnotatedClass( Department.class )
                .getMetadataBuilder()
                .build();
        testSessionFactory = metadata.getSessionFactoryBuilder()
                .build();
    }

    @Before
    public void setUp() throws Exception {
        targetObject = new EmployeeDaoImpl(testSessionFactory);
    }

    @After
    public void tearDown() throws Exception {
        targetObject = null;
    }

    @Test
    @SneakyThrows
    public void create() {
        //Given
        Connection conn = testMysqlJdbcDataSource.getConnection();

        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(EmployeeDaoImpl.class.getResourceAsStream("DepartmentDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

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
        department.setDepartmentName("TEST_NAME");

        employee.setEmployeeDetail(employeeDetail);
        employee.setDepartment(department);
        employeeDetail.setEmployee(employee);

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

        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void findById() {
        //given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(EmployeeDaoImpl.class.getResourceAsStream("EmployeeDetailTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //when
        final Employee em = targetObject.findById(101);

        //then
        assertEquals("+375296101011", em.getCellPhone());
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    public void update() {
    }

    @Test
    @SneakyThrows
    public void delete() {
        //given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(EmployeeDaoImpl.class.getResourceAsStream("EmployeeDetailTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //when
        Employee employee = targetObject.findById(101);
        assertNotNull(employee);
        targetObject.delete(employee);

        //then
        Connection connection = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = connection.createStatement().executeQuery("select count(*) from t_employeedetail;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(0, actualSize);
    }
}