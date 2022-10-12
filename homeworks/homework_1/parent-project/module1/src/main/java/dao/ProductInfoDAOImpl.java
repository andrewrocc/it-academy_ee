package dao;

import model.ProductInfo;
import dataSource.MysqlDataSource;

import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;

public class ProductInfoDAOImpl implements ProductInfoDAO {

    private MysqlDataSource datasource;

    public ProductInfoDAOImpl() throws ClassNotFoundException {
        this.datasource = new MysqlDataSource();
    }

    @Override
    public void create(ProductInfo productInfo) {

    }

    @Override
    public List<ProductInfo> readAll() {
        List<ProductInfo> products = new ArrayList<>();
        try {
            final Connection connection = datasource.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM product_info");
            while (resultSet.next()) {
                final ProductInfo productInfo = new ProductInfo();
                productInfo.setId(resultSet.getInt("id"));
                productInfo.setName(resultSet.getString("name"));
                productInfo.setPrice(resultSet.getDouble("price"));
                products.add(productInfo);
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    @Override
    public void update(ProductInfo productInfo) {

    }

    @Override
    public void delete(ProductInfo productInfo) {

    }
}
