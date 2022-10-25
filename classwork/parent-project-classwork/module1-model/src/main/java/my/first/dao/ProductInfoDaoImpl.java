package my.first.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import my.first.model.ProductInfo;
import org.hibernate.SessionFactory;
import my.first.MysqlJdbcDataSource;
import my.first.MysqlSessionFactory;

import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;

public class ProductInfoDaoImpl implements ProductInfoDao {

    private final MysqlJdbcDataSource dataSource;

    private final SessionFactory sessionFactory;

    public ProductInfoDaoImpl() throws ClassNotFoundException {
        this.dataSource = new MysqlJdbcDataSource();
        this.sessionFactory = MysqlSessionFactory.getInstance();
    }

    public ProductInfoDaoImpl(MysqlJdbcDataSource dataSource, SessionFactory sessionFactory) {
        this.dataSource = dataSource;
        this.sessionFactory = sessionFactory;
    }

    // hibernate
    @Override
    public void create(ProductInfo productInfo) {
        Transaction tx = null;
        try (Session sess = sessionFactory.openSession()) {
            tx = sess.beginTransaction();
            sess.saveOrUpdate(productInfo);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            throw ex;
        }
    }

    // jdbc
    @Override
    public List<ProductInfo> readAll() {
        List<ProductInfo> products = new ArrayList<>();
        try {
            final Connection connection = dataSource.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM product_info");
            while (resultSet.next()) {
                final ProductInfo productInfo = new ProductInfo();
                productInfo.setId(resultSet.getInt("id"));
                productInfo.setName(resultSet.getString("name"));   //name
                productInfo.setPrice(resultSet.getDouble("price"));
                products.add(productInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void update(ProductInfo productInfo) {

    }

    @Override
    public void delete(ProductInfo productInfo) {
        Transaction tx = null;
        try (Session sess = sessionFactory.openSession()) {
            tx = sess.beginTransaction();
            sess.delete(productInfo);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            throw ex;
        }
    }
}
