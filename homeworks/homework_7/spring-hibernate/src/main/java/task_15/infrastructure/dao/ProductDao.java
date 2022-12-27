package task_15.infrastructure.dao;

import task_15.infrastructure.model.Product;

public interface ProductDao {

    void addProduct(Product p);

    Product getById(short id);

    void update(Product p);

    void delete(Product p);
}
