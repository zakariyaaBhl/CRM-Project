package com.bhl.crm.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import javax.sql.DataSource;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.bhl.crm")
@PropertySource({ "classpath:persistence-mysql.properties", "classpath:security-persistence-mysql.properties" })
@EnableTransactionManagement
public class CrmConfig implements WebMvcConfigurer{
	
	@Autowired
	private Environment env;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver=new  InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean
	public DataSource datasource() {
		// create connection pool
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
			
			//set the JDBC Driver:
			try {
				dataSource.setDriverClass(env.getProperty("jdbc.driver"));
			} catch (PropertyVetoException e) {
				new RuntimeException(e);
			}
		
			// for sanity's sake, let's log url and user ... just to make sure we are reading the data
			logger.info("====>>>>>jdbc.url=" + env.getProperty("jdbc.url"));
			logger.info("====>>>>>jdbc.user=" + env.getProperty("jdbc.user"));
			
			// set database connection props
			dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
			dataSource.setUser(env.getProperty("jdbc.user"));
			dataSource.setPassword(env.getProperty("jdbc.password"));
			
			// set connection pool props
			dataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
			dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
			dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
			dataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));
		
		return dataSource;
	}
	
	/*-- The next method handles the Hibernate properties --*/
	private Properties getHibernateProperties() {
		
		// set hibernate properties
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.put("hibernate.hbm2ddl.auto", "update");
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		return props;
		}
	
	/*-- 
	 * The next method creates the Hibernate session factory based on the datasource and configuration properties
	 * Setup Hibernate session factory : session factory is what hibernate uses to talk to our DB 
	 * --*/
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		// create session factorys
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean(); 
		
		sessionFactory.setDataSource(datasource());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		
		sessionFactory.setHibernateProperties(getHibernateProperties());
		//factoryBean.setAnnotatedClasses(User.class);
		
		return sessionFactory;
	}
	
	
	/*-- The next method creates the securityDataSource bean. This points to the security database for user id, passwords and roles. 
	 * 	 define a bean for security data source
	 * --*/
	
	@Bean
	public DataSource securityDataSource() {
		// create connection pool
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
			
			//set the JDBC Driver:
			try {
				securityDataSource.setDriverClass(env.getProperty("com.mysql.jdbc.Driver"));
			} catch (PropertyVetoException e) {
				new RuntimeException(e);
			}
		
			// for sanity's sake, let's log url and user ... just to make sure we are reading the data
			logger.info("====>>>>>security.url=" + env.getProperty("security.jdbc.url"));
			logger.info("====>>>>>security.user=" + env.getProperty("security.jdbc.user"));
			
			// set database connection props
			securityDataSource.setJdbcUrl(env.getProperty("security.jdbc.url"));
			securityDataSource.setUser(env.getProperty("security.jdbc.user"));
			securityDataSource.setPassword(env.getProperty("security.jdbc.password"));
			
			// set connection pool props
			securityDataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
			securityDataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("security.connection.pool.maxPoolSize")));
			securityDataSource.setMinPoolSize(Integer.parseInt(env.getProperty("security.connection.pool.minPoolSize")));
			securityDataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("security.connection.pool.maxIdleTime")));
		
		return securityDataSource;
	}
	

	/*-- The next method configures the Hibernate transaction manager --*/
	@Bean
	public HibernateTransactionManager transactionManager() {
			// setup transaction manager based on session factory
			HibernateTransactionManager transactionManager = new HibernateTransactionManager();
			transactionManager.setSessionFactory(getSessionFactory().getObject());
			
		return transactionManager;
	}
	
	
	
	
	/*-- Finally, the app is going to use static web resources such as css, images, js etc. So we add a resource handler. --*/
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/resources/**")
			.addResourceLocations("/resources/");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
