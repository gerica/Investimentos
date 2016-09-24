package br.cs.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages={"br"}, excludeFilters={@org.springframework.context.annotation.ComponentScan.Filter(type=org.springframework.context.annotation.FilterType.ANNOTATION, value={org.springframework.web.servlet.config.annotation.EnableWebMvc.class})})
@PropertySource({"application.properties"})
@EnableJpaRepositories(basePackages={"br.cs.*"})
@EnableTransactionManagement
public class RootConfig
{
  private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
  private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
  private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
  private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
  private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
  private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
  public static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "br.cs.*";
  @Autowired
  private Environment env;
  
  @Bean
  public DataSource dataSource()
  {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    
    dataSource.setDriverClassName(this.env.getRequiredProperty("db.driver"));
    dataSource.setUrl(this.env.getRequiredProperty("db.url"));
    dataSource.setUsername(this.env.getRequiredProperty("db.username"));
    dataSource.setPassword(this.env.getRequiredProperty("db.password"));
    
    return dataSource;
  }
  
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory()
  {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(dataSource());
    entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
    entityManagerFactoryBean.setPackagesToScan(new String[] { "br.cs.*" });
    
    entityManagerFactoryBean.setJpaProperties(hibProperties());
    
    return entityManagerFactoryBean;
  }
  
  private Properties hibProperties()
  {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", this.env.getRequiredProperty("hibernate.dialect"));
    properties.put("hibernate.show_sql", this.env.getRequiredProperty("hibernate.show_sql"));
    return properties;
  }
  
  @Bean
  public JpaTransactionManager transactionManager()
  {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return transactionManager;
  }
}
