
package service;


import java.util.logging.Logger;

import model.Customer;
import model.SavingAccountM;


/**
 * @author trainee
 *
 */
public class AccountServiceImpl implements IAccountService {

  final Logger logMe = Logger.getLogger(AccountServiceImpl.class.getName());

  public IAccountDAO accountDao = new SavingAccountImpl();
  SavingAccountM aAct = new SavingAccountM();
  // Map<accountNumber,SavingAccountM> list = new HashMap();

  public SavingAccountM Withdraw(double amtToWithdraw) throws WithdrawException {

    if (amtToWithdraw > aAct.getBalance() ) {

      throw new WithdrawException("Insufficient Balance in your Account : Do deposit first ", aAct.getAccountNumber(),
          aAct.getBalance());


    } else {

     //  list=accountDao.Withdraw(amtToWithdraw);

    }
    return aAct;

  }

 
 
    
  
  public SavingAccountM Deposit(double amount) {

    return null;
   }


  public void UpdateDetails(Customer customer, String checkAccountNumber, int newChange) {
    // TODO Auto-generated method stub
    
  }

}
