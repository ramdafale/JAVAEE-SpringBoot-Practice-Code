package controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import service.GoodsService;

/**
 * @author Ram
 *
 */
public class GroceryMain {

	public static void Main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("dataSource");

		GoodsService goodsService = null;
		int dummy = goodsService.addGoods(1, "soap", 4, 20.0);
		System.out.println(dummy);

	}

}
