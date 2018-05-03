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

@Configuration
@ComponentScan({"service","repository","controller"})
@PropertySource("classpath:database.properties")
public class GroceryAnnotation {

	@Autowired
	Environment environment;

/*	private final String URL = "jdbc:mysql://localhost:3306/grocery";
	private final String USER = "root";
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String PASSWORD = "root";*/
	
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
	JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}
}
