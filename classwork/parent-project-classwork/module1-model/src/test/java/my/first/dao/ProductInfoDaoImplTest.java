package my.first.dao;

import org.junit.*;
import lombok.SneakyThrows;
import my.first.model.ProductInfo;
import org.hibernate.boot.Metadata;
import org.dbunit.dataset.IDataSet;
import org.hibernate.SessionFactory;
import my.first.MysqlJdbcDataSource;
import org.hibernate.boot.MetadataSources;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;

import static org.junit.Assert.assertEquals;

public class ProductInfoDaoImplTest {

    static MysqlJdbcDataSource testMysqlJdbcDataSource;

    static IDatabaseConnection iDatabaseConnection;

    static SessionFactory testSessionFactory;

    ProductInfoDao targetObject;

    @BeforeClass
    @SneakyThrows
    public static void init() {
        testMysqlJdbcDataSource = new MysqlJdbcDataSource("eshop_test.jdbc.properties");
        iDatabaseConnection = new MySqlConnection(testMysqlJdbcDataSource.getConnection(), "eshop_test");

        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate_test.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources( standardRegistry )
                .addAnnotatedClass( ProductInfo.class )
                .getMetadataBuilder()
                .build();
        testSessionFactory = metadata.getSessionFactoryBuilder()
                .build();
    }

    @Before
    public void setUp() throws Exception {
        targetObject = new ProductInfoDaoImpl(testMysqlJdbcDataSource, testSessionFactory);
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