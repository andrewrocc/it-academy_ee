package task_15.infrastructure.config;

import org.hibernate.cfg.Environment;
import task_15.infrastructure.model.Product;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "task_15")
@PropertySource(value = { "classpath:/product.properties", "classpath:/hibernate.properties" })
public class ProjectConfig {

    @Bean
    public Properties hibernateProperties(@Value("${hibernate.show_sql}") String showSql,
                                          @Value("true") String debug,
                                          @Value("${hibernate.dialect}") String dialect,
                                          @Value("${hibernate.format_sql}") String format) {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.show_sql", showSql);
        hibernateProperties.put("debug", debug);
        hibernateProperties.put("hibernate.dialect", dialect);
        hibernateProperties.put("hibernate.format_sql", format);
        hibernateProperties.put(Environment.HBM2DDL_AUTO, "update");

        return hibernateProperties;
    }

    @Bean
    public DataSource dataSource(@Value("${url}") String url,
                                 @Value("${driver}") String driverClassName,
                                 @Value("${username}") String username,
                                 @Value("${password}") String password,
                                 @Value("true") boolean removeAbandonedOnBorrow,
                                 @Value("20") int initialSize,
                                 @Value("30") int maxTotal) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setRemoveAbandonedOnBorrow(removeAbandonedOnBorrow);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxTotal(maxTotal);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource,
                                                  Properties hibernateProperties) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setAnnotatedClasses(
                Product.class
        );
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
