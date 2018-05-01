package controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import repository.CustomerDAO;
import repository.CustomerDAOImpl;
import repository.GoodsDAO;
import repository.GoodsDAOImpl;
import repository.RetailerDAO;
import repository.RetailerDAOImpl;
import repository.SupplierDAO;
import repository.SupplierDAOImpl;

@Configuration
@ComponentScan("com.journaldev.spring")
@PropertySource("classpath:database.properties")
public class AppJavaConfig {


	@Autowired
	Environment environment;

	private final String URL = "jdbc:mysql://localhost:3306/mydb1";
	private final String USER = "root";
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String PASSWORD = "root";

	@Bean
	DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(environment.getProperty(URL));
		driverManagerDataSource.setUsername(environment.getProperty(USER));
		driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
		driverManagerDataSource.setDriverClassName(environment.getProperty(DRIVER));
		return driverManagerDataSource;
	}
	

	@Bean
	public CustomerDAO CustomerDaoBean() {
		return new CustomerDAOImpl();
	}

	@Bean
	public GoodsDAO GoodsDAOBean() {
		return new GoodsDAOImpl();
	}
	@Bean
	public SupplierDAO SupplierDAOBean() {
		return new SupplierDAOImpl();
	}
	@Bean
	public RetailerDAO RetailerDAOBean() {
		return new RetailerDAOImpl();
	}
	
	
	
}
