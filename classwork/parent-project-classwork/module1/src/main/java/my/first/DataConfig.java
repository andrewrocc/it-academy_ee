package my.first;

import lombok.SneakyThrows;
import my.first.model.*;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "my.first")
@PropertySource(value = {"classpath:/eshop.jdbc.properties",
        "classpath:/hibernate.properties"})
public class DataConfig {

    public static final String JDBC_PROPERTIES_FILE_NAME = "eshop.jdbc.properties";

    public static final String HIBERNATE_PROPERTIES_FILE_NAME = "hibernate.properties";

    private static Properties jdbcProperties;


    @SneakyThrows
    public static Properties getJdbcProperties(String propertyFileName) {
        if (jdbcProperties == null) {
            jdbcProperties = new Properties();
            jdbcProperties.load(MysqlJdbcDataSource.class
                    .getClassLoader()
                    .getResourceAsStream(propertyFileName));
        }
        return jdbcProperties;
    }


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
                ProductInfo.class,
                Employee.class,
                EmployeeDetail.class,
                Department.class,
                Meeting.class
        );
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }

}
