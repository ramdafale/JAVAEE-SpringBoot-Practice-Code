/**
 * 
 */
package repository;

import java.util.ArrayList;
import java.util.List;

import model.Bank;



/**
 * @author trainee
 *
 */
public class BankDaoImpl implements IBankDao {

  
  public List<Bank> addBank(Bank bank) {
    final List<Bank> list=new ArrayList();
  
    list.add(bank);
    // TODO Auto-generated method stub
    return list;
    
  }
  
  
  
}
