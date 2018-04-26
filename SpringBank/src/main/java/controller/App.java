package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import model.Bank;
import model.Customer;
import model.SavingAccountM;

public class App extends Bank {

  /*
   * Returns nothing
   */
  public static void main(final String[] args) {

    final Logger logMe = Logger.getLogger(App.class.getName());

    final ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

    Bank bank = ctx.getBean("bank", Bank.class);

    List<Bank> banklist = new ArrayList<Bank>();
    System.out.println();
    service.IBankService bankService = ctx.getBean("bankService", service.BankServiceImpl.class);
    banklist = bankService.addBank(bank);
    
    

    final Customer customer1 = ctx.getBean(Customer.class);

    final SavingAccountM mySaving = (SavingAccountM) ctx.getBean("savingAccount1");

    final List<SavingAccountM> listSavingAccount = new ArrayList<SavingAccountM>();

    listSavingAccount.add(mySaving);

    for (final SavingAccountM item : listSavingAccount) {
      logMe.info("retrieved element: " + item);
    }

    mySaving.getAccountNumber();
    mySaving.getBalance();
    mySaving.getCustomer();
    mySaving.getInterestRate();

    logMe.info("Welcome To Bank : " + customer1.getFname());
    logMe.info("Your balance is: " + mySaving.getBalance());
    
    

    logMe.info("Update my Account");

    customer1.setEmail("newEmailID@example.com");

    logMe.info("After  Update ");
    System.out.println(customer1.getEmail());

    
    
    if (listSavingAccount.contains(mySaving)) {
      logMe.info("Account found");
    } else {
      logMe.info("Account not found");
    }

    
    
    //Remove my account
    listSavingAccount.remove(mySaving);
    
    if (listSavingAccount.contains(mySaving)) {
      logMe.info("Account found");
    } else {
      logMe.info("Account not found");
    }

  }

}
