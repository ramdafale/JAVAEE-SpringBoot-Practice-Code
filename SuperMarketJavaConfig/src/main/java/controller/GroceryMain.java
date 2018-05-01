package controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import repository.CustomerDAOImpl;
import repository.GoodsDAOImpl;
import repository.RetailerDAOImpl;
import repository.SupplierDAOImpl;

/**
 * @author Ram
 *
 */
public class GroceryMain {

	public static void main(String[] args) {
		
		

        ApplicationContext context = new AnnotationConfigApplicationContext(AppJavaConfig.class);
		//ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("dataSource");

		 CustomerDAOImpl dao=(CustomerDAOImpl) context.getBean("cdao");  
		
		
		/*    int status=dao.addCustomer(new Customer(106,"Amit","amravati","debit card"));  
		    System.out.println(status); 
		
		*/
		 
		    
		   /* int status1=dao.updateCustomer(104,"Mahesh"); 
		    System.out.println(status1); */
		     
		       
		    
		    
	    
		//    Customer e=new Customer(); 
		   // e.setCustomerId(520);
		 /*
		    int status2=dao.removeCustomer(104); 
		
		    System.out.println(status2); 
		
		 
		*/
		 
		 GoodsDAOImpl gdao =(GoodsDAOImpl) context.getBean("gdao");		//goods bean + jdbc template
		
		/* 
		 //@ add goods
		 int status3 = gdao.addGoods(12, "Doodle", 5, 50.12);
		 System.out.println(status3);
		 
		 int status4 = gdao.addGoods(13, "Soap", 5, 250.12);
		 System.out.println(status4);
		*/
		
	/*
		 //@remove goods
		 int status4 = gdao.removeGoods(12);
		 System.out.println(status4);
		
	*/	 
		 /*
		//@update goods
		 int status5 = gdao.updateGoods(12,"Pen");
		 System.out.println(status5);
		 */
		
		 
		 SupplierDAOImpl sdao =(SupplierDAOImpl) context.getBean("sdao");		//goods bean + jdbc template
		 
		 
	/*	 //@ add supplier
		 int status6 = sdao.addSupplier(22, "ram saheb", "wardha", 12, 211, 21211.2);
		 System.out.println(status6);
		 */

	/*
		 //@remove supplier
		 int status7 = sdao.removeSupplier(21);
		 System.out.println(status7);
		
		 */
		 
		 
		 
		//@update supplier
		
		 
		 //int status8 = sdao.updateSupplier(22, "Prateek saheb", "Amravati", 121, 211, 2011.2);
		 //int status8 = sdao.updateSupplier(22, "Prateek saheb");
		 //System.out.println(status8);
		 
		
		RetailerDAOImpl rdao =(RetailerDAOImpl) context.getBean("rdao");
		
		
		
	/*		 //@ add supplier
		 int status9 = rdao.addRetailer("jasonRoy","England");
		 System.out.println(status9);
		 */
		
		
		//rdao.viewCustomer(106);
		//rdao.getAllGoods();
		rdao.viewSupplier(22);
		

	}

}
		
		
		
		
		
		
		
		
		
		
		
		
		/*GoodsService goodsService = null;
		int dummy = goodsService.addGoods(1, "soap", 4, 20.0);
		System.out.println(dummy);
		
		CustomerDAO customerDAO = context.getBean(CustomerDAO.class);
		RetailerDAO retailer = context.getBean(RetailerDAO.class);
		
		
		System.out.println("List of Goods is:");

		for (Retailer p : retailer.viewGoods()) {
			
			System.out.println(p);
		}
		*/
		
		
		
		
		
		

