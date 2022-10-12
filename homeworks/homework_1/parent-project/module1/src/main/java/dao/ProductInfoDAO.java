package dao;

import model.ProductInfo;

import java.util.List;

public interface ProductInfoDAO {

    void create(ProductInfo productInfo);

    List<ProductInfo> readAll();

    void update(ProductInfo productInfo);

    void delete(ProductInfo productInfo);
}
