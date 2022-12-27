package task_15.infrastructure.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import task_15.infrastructure.model.Product;

@Repository
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

    private final SessionFactory sessionFactory;

    @Override
    public void addProduct(Product p) {
        sessionFactory.getCurrentSession().saveOrUpdate(p);
    }

    @Override
    public Product getById(short id) {
        return sessionFactory.getCurrentSession().get(Product.class, id);
    }

    @Override
    public void update(Product p) {
        addProduct(p);
    }

    @Override
    public void delete(Product p) {
        Product product = sessionFactory.getCurrentSession().load(Product.class, p.getId());
        sessionFactory.getCurrentSession().delete(product);
    }
}
