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

/**
 * @class this is java configuration file
 */
@Configuration
@ComponentScan({ "service", "repository", "controller" })
@PropertySource(value = { "classpath:database.properties" })
public class AppJavaConfig {

	@Autowired
	Environment environment;

	@Bean
	DataSource dataSource() {
		final DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(environment.getProperty("url"));
		driverManagerDataSource.setUsername(environment.getProperty("dbuser"));
		driverManagerDataSource.setPassword(environment.getProperty("dbpassword"));
		driverManagerDataSource.setDriverClassName(environment.getProperty("driver"));
		return driverManagerDataSource;
	}

	@Bean
	JdbcTemplate jdbcTempalte(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}


	/*
	 * @Bean public CustomerDAO customerDaoBean() { return new CustomerDAOImpl(); }
	 * 
	 * @Bean public GoodsDAO goodsDAOBean() { return new GoodsDAOImpl(); }
	 * 
	 * @Bean public SupplierDAO supplierDAOBean() { return new SupplierDAOImpl(); }
	 */

	/**
	 * this is CustomerService bean used to invoke the services
	 */
	/*
	 * @Bean public RetailerDAO retailerDAOBean() { return new RetailerDAOImpl(); }
	 * 
	 *//**
		 * this is CustomerService bean used to invoke the services
		 *//*
			 * @Bean public CustomerService customerService() { return new
			 * CustomerServiceImpl(); }
			 */

	/**
	 * this is GoodsService bean used to invoke the services
	 */
	/*
	 * @Bean public GoodsService goodsService() { return new GoodsServiceImpl(); }
	 * 
	 * @Bean public SupplierService supplierService() { return new
	 * SupplierServiceImpl(); }
	 * 
	 * @Bean public RetailerService retailerService() { return new
	 * RetailerServiceImpl(); }
	 */

}
