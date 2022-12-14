package my.first.dao;

import lombok.SneakyThrows;
import my.first.dao.impl.DepartmentDaoImpl;
import my.first.model.Department;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class DepartmentDaoImplTest extends BaseDaoTest {

    @Autowired
    DepartmentDao targetObject;

    @Before
    public void setUp() { }

    @After
    @SneakyThrows
    public void tearDown() {
        Connection conn = testMysqlJdbcDataSource.getConnection();
        conn.createStatement().executeUpdate("delete from t_department;");
        conn.close();
        targetObject = null;
    }

    @Test
    @SneakyThrows
    public void create() {
        //Given
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_department;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Department department = new Department();
        department.setDepartmentName("TestDepartment");

        //When
        targetObject.create(department);

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from t_department;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
    }

    @Test
    @SneakyThrows
    public void findAllDepartmentNames() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(DepartmentDaoImplTest.class.getResourceAsStream("DepartmentDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        List<String> departmentNames = targetObject.findAllDepartmentNames();

        //Then
        assertEquals(1, departmentNames.size());
        assertEquals("Hidden", departmentNames.get(0));
    }

    @Test
    @SneakyThrows
    public void findById() {
        //given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(EmployeeDaoImplTest.class.getResourceAsStream("DepartmentDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //when
        Department department = targetObject.findById(1);

        //then
        assertEquals(1, department.getId());
//        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void delete() {
        //given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(EmployeeDaoImplTest.class.getResourceAsStream("DepartmentDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //when
        targetObject.delete(1);

        //then
        ResultSet rs = testMysqlJdbcDataSource.getConnection()
                .createStatement()
                .executeQuery("select count(*) from t_department");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(0, actualSize);
    }
}