
package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import bankapplication.Apps;
import model.SavingAccountM;
import repository.IAccountDAO;

/**
 * @author trainee
 *
 */
public class AccountServiceImpl implements IAccountService {

  final Logger logMe = Logger.getLogger(Apps.class.getName());

  public IAccountDAO accountDao = new repository.SavingAccountImpl();
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

    
    
   // public SavingAccountM Deposit(double amtToDeposit) throws WithdrawException {

   //   if (amtToDeposit > aAct.getBalance() ) {

       // throw new WithdrawException("Insufficient Balance in your Account : Do deposit first ", aAct.getAccountNumber(),
            //aAct.getBalance());


     // }
    
   return null;
  }

}
