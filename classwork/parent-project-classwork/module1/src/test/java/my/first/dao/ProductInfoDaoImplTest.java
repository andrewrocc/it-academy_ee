package my.first.dao;

import my.first.TestDataConfig;
import org.junit.*;
import lombok.SneakyThrows;
import my.first.model.ProductInfo;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataConfig.class)
public class ProductInfoDaoImplTest extends BaseDaoTest {

    @Autowired
    ProductInfoDao targetObject;

    @Before
    public void setUp() throws Exception {
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
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from product_info;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);

        ProductInfo productInfo = new ProductInfo();
        productInfo.setName("test");
        productInfo.setPrice(111.222);

        //When
        targetObject.create(productInfo);

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from product_info;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        conn.createStatement().executeUpdate("truncate table product_info;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void readAll() {
        //given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(ProductInfoDaoImplTest.class.getResourceAsStream("ProductInfoDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //when
        List<ProductInfo> productInfo = targetObject.readAll();

        //then
        assertEquals(productInfo.size(), 10);
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}