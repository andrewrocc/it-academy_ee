package my.first.dao;

import lombok.SneakyThrows;
import my.first.model.Department;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DepartmentDaoImplTest extends BaseDaoTest {

    DepartmentDaoImpl targetObject;

    @Before
    public void setUp() throws Exception {
        targetObject = new DepartmentDaoImpl(testSessionFactory);
    }

    @After
    public void tearDown() {
        targetObject = null;
    }

    @Test
    @SneakyThrows
    public void create() {
        //given
        Connection connection = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = connection.createStatement().executeQuery("select count(*) from t_department");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);

        Department department = new Department();
        department.setDepartmentName("Test");

        //when
        targetObject.create(department);

        //then
        rs = connection.createStatement().executeQuery("select count(*) from t_department");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        connection.createStatement().executeUpdate("delete from t_department");
    }

    @Test
    @SneakyThrows
    public void findById() {
        //given
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(DepartmentDaoImpl.class.getResourceAsStream("DepartmentDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //when
        Department department = targetObject.findById(1);

        //then
        assertEquals(1, department.getId());
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @Ignore
    @SneakyThrows
    public void delete() {
        //given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(DepartmentDaoImplTest.class.getResourceAsStream("DepartmentDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //when
        Department department = targetObject.findById(1);
        assertNotNull(department);
        targetObject.delete(department);

        //then
        Connection connection = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = connection.createStatement().executeQuery("select count(*) from t_department");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        connection.createStatement().executeUpdate("delete from t_department");
        rs.close();
        connection.close();
    }
}
















