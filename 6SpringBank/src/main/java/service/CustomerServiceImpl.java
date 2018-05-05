package service;

import java.util.List;

import model.Customer;
import repository.CustomerDAOImpl;
import repository.ICustomerDao;


public class CustomerServiceImpl implements ICustomerService {

  
  public ICustomerDao customerDao=new CustomerDAOImpl();

  public List<Customer> addCustomer(Customer customer) {
  
    return customerDao.addCustomer(customer);
  
}
}
