/**
 * 
 */
package service;

import java.util.List;

import model.Bank;
import repository.IBankDao;

/**
 * @author trainee
 *
 */
public class BankServiceImpl implements IBankService {

  public IBankDao bankDao;

  public BankServiceImpl() {
    // TODO Auto-generated constructor stub
  }

  /**
   * @param bankDao
   */
  public BankServiceImpl(IBankDao bankDao) {
    super();
    this.bankDao = bankDao;
  }

  public List<Bank> addBank(Bank bank) {
    // TODO Auto-generated method stub
    return bankDao.addBank(bank);
  }

}
