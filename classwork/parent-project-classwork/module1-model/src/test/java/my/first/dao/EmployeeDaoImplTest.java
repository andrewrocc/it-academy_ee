package my.first.dao;

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
        //given
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet set = conn.createStatement().executeQuery("select count(*) from t_employeedetail;");
        set.next();
        int initialSize = set.getInt(1);
        assertEquals(0, initialSize);

        Employee em = new Employee();
        em.setFirstName("Sasha");
        EmployeeDetail em_d = new EmployeeDetail();
        em_d.setCountry("BELARUS");

        em.setEmployeeDetail(em_d);
        em_d.setEmployee(em);

        //when
        targetObject.create(em);

        //then
        set = conn.createStatement().executeQuery("select count(*) from t_employeedetail;");
        set.next();
        int actualSize = set.getInt(1);
        assertEquals(1, actualSize);
        conn.createStatement().executeUpdate("truncate table t_employeedetail;");
        conn.createStatement().executeUpdate("truncate table t_employee");
        conn.close();
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
    public void delete() {
    }
}