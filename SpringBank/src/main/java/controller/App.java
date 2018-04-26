package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
  @SuppressWarnings({ "PMD.SystemPrintln", "PMD.LawOfDemeter" })
  public static void main(final String[] args) {

    final Logger LOGGER = Logger.getLogger(Apps.class.getName());

    final ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

    final Customer customer1 = ctx.getBean(Customer.class);

    final SavingAccountM mySaving = (SavingAccountM) ctx.getBean("savingAccount1");

    // Resource r=new ClassPathResource("beans.xml");
    // BeanFactory factory=new XmlBeanFactory(r);

    // Customer s=(Customer)factory.getBean("customer");

    final List<Customer> listSavingAccount = new ArrayList<Customer>();

    listSavingAccount.add(customer1);

    for (Customer item : listSavingAccount) {
      LOGGER.info("retrieved element: " + item);
    }

    mySaving.getAccountNumber();
    mySaving.getBalance();
    mySaving.getCustomer();
    mySaving.getInterestRate();

    // final Logger LOGGER = Logger.getLogger(App.class.getName());

    System.out.println("Welcome To Bank" + customer1.getFname()); // Creating New account for cust
                                                                  

  }

}
