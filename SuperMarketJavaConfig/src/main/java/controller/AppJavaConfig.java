package controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import repository.CustomerDAO;
import repository.CustomerDAOImpl;
import repository.GoodsDAO;
import repository.GoodsDAOImpl;
import repository.RetailerDAO;
import repository.RetailerDAOImpl;
import repository.SupplierDAO;
import repository.SupplierDAOImpl;
import service.CustomerService;
import service.CustomerServiceImpl;
import service.GoodsService;
import service.GoodsServiceImpl;
import service.RetailerService;
import service.RetailerServiceImpl;
import service.SupplierService;
import service.SupplierServiceImpl;

/**
 * @class this is java configuration file
 */
@Configuration
@ComponentScan("controller")
@PropertySource("classpath:database.properties")
public class AppJavaConfig {

	/**
	 *  this is java configuration file
	 */
	AppJavaConfig() {
	}

	/**
	 * @injection
	 * 
	 */
	@Autowired
	Environment environment;

	/**
	 * @url it is url for a connecting to mysql server and database
	 * 
	 */
	private static final String URL = "url";

	/**
	 * @USER it is USER for a connecting to mysql server with username
	 * 
	 */
	private static final String USER = "dbuser";
	/**
	 * @DRIVER it is url for a connecting to mysql server with DRIVER
	 * 
	 */
	private static final String DRIVER = "driver";

	/**
	 * @DRIVER it is url for a connecting to mysql server with PASSWORD
	 * 
	 */
	private static final String PASSWORD = "dbpassword";

	
	
	@Bean
	JdbcTemplate jdbcTempalte(DataSource dataSource)
	{JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource);
	return jdbcTemplate;
		
	}
	
	
/**
 * @bean this is used to insert properties  of database.
 */
	
	
	@Bean
	DataSource dataSource() {
		final DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(environment.getProperty(URL));
		driverManagerDataSource.setUsername(environment.getProperty(USER));
		driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
		driverManagerDataSource.setDriverClassName(environment.getProperty(DRIVER));
		return driverManagerDataSource;
	}

	@Bean
	public CustomerDAO customerDaoBean() {
		return new CustomerDAOImpl();
	}

	@Bean
	public GoodsDAO goodsDAOBean() {
		return new GoodsDAOImpl();
	}

	@Bean
	public SupplierDAO supplierDAOBean() {
		return new SupplierDAOImpl();
	}


	/**
	 *  this is CustomerService bean used to invoke the 
	 *  services
	 */
	@Bean
	public RetailerDAO retailerDAOBean() {
		return new RetailerDAOImpl();
	}

	/**
	 *  this is CustomerService bean used to invoke the 
	 *  services
	 */
	@Bean
	public CustomerService customerService() {
		return new CustomerServiceImpl();
	}


/**
 *  this is GoodsService bean used to invoke the 
 *  services
 */
	@Bean
	public GoodsService goodsService() {
		return new GoodsServiceImpl();
	}

	@Bean
	public SupplierService supplierService() {
		return new SupplierServiceImpl();
	}

	@Bean
	public RetailerService retailerService() {
		return new RetailerServiceImpl();
	}

}
