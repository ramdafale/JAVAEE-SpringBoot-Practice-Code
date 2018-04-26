/**
 * 
 */
package service;

import java.util.ArrayList;
import java.util.List;


import model.SavingAccountM;
import repository.IAccountDAO;

/**
 * @author trainee
 *
 */
public class AccountServiceImpl implements IAccountService {

  public IAccountDAO accountDao=new repository.SavingAccountImpl();
SavingAccountM aAct=new SavingAccountM();
  List list=new ArrayList();

  public List Withdraw(double amtToDepo) {
   
   
     if(amtToDepo<0)
    {
      
      try {
        throw new WithdrawException("amt can not be less that zero of  ", aAct.getAccountNumber(), aAct.getBalance());
      } catch (WithdrawException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      
      //LOGGER.info("Negative amount is not possible");
    }
    else
    {
      
      list=accountDao.Withdraw(amtToDepo);
     
    }
    return list;
  
    

}




  public List Deposit(double amount) {
    // TODO Auto-generated method stub
    return null;
  }

}
