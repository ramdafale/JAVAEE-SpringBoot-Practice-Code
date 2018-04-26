package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import bankapplication.Apps;
import model.Bank;
import model.Customer;
import model.SavingAccountM;

public class App extends Bank {
  
  
  /**
   * Returns an nothing 
   */
  public static void main(final String[] args) {
    
    
    final  Logger LOGGER = Logger.getLogger(Apps.class.getName());
    

    final ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

    final Customer customer1 = ctx.getBean(Customer.class);
    
    
    SavingAccountM jc=(SavingAccountM) ctx.getBean("savingAccount1");

    // Resource r=new ClassPathResource("beans.xml");
    // BeanFactory factory=new XmlBeanFactory(r);

    // Customer s=(Customer)factory.getBean("customer");

    final List<Customer> listSavingAccount = new ArrayList<Customer>();

    listSavingAccount.add(customer1);

    for (Customer item : listSavingAccount) {
      LOGGER.info("retrieved element: " + item);
    }

    
   

    jc.getAccountNumber();
    jc.getBalance();
    jc.getCustomer();
    jc.getInterestRate();
    
    
    
    
    
    
    
    
    
    
    
    
    
    // CustomerService service = new CustomerServiceImpl();
    // for Testing Purpose
    // System.out.println(service.findAll().get(0).getFname());

    // final Logger LOGGER = Logger.getLogger(App.class.getName());

    
      System.out.println("Welcome To Bank" + customer1.getFname()); // Creating New account for customer
      
   
    
      
     
  }

}
