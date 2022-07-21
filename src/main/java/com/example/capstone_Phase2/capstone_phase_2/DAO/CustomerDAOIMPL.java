package com.example.capstone_Phase2.capstone_phase_2.DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.capstone_Phase2.capstone_phase_2.Entity.Customer;
@Component
public class CustomerDAOIMPL implements CustomerDAO {

	 private static List<Customer> customers=new ArrayList<Customer>();
	    static Integer counter= 1000;
	    static {
	    	customers.add(new Customer(++counter,"Rohit","Guttula","rohitguttula@gmail.com", null));
	    	customers.add(new Customer(++counter,"Bindu","Guttula","binduguttula@gmail.com", null));
	    	customers.add(new Customer(++counter,"priya","kiddy","priyakiddy@gmail.com", null));
	    }
	@Override
	public Customer CreateCustomer(Customer customer) {
		Customer saveCustomer=customer;
		saveCustomer.setCust_id(++counter);
		customers.add(saveCustomer);
		return saveCustomer;
	}

	@Override
	public Customer GetCustomerByID(Integer customerId) {
		Customer tempcustomerId=null;
		for(Customer customer:customers) {
			if(customer.getCust_id().equals(customerId)) {
				tempcustomerId=customer;
				break;
			}
		}
		// TODO Auto-generated method stub
		return tempcustomerId;
	}

	@Override
	public List<Customer> GetAllCustomers() {
		// TODO Auto-generated method stub
		return customers;
	}

	@Override
	public Customer updateCustomer(Integer customerId, Customer cust) {
		Customer tempcustomerId=null;
		for(Customer customer:customers) {
			if(customer.getCust_id().equals(customerId)) {
				customer.setEmail(cust.getEmail());
				customer.setFirst_Name(cust.getFirst_Name());
				customer.setLast_Name(cust.getLast_Name());
				tempcustomerId=customer;
				break;
			}
		}
		// TODO Auto-generated method stub
		return tempcustomerId;
	}

	@Override
	public Customer deleteCustomer(Integer customerId) {
		Customer tempcustomerId=null;
		Iterator<Customer> itr=customers.iterator();
		while(itr.hasNext()) {
			Customer cust=itr.next();
			if(cust.getCust_id().equals(customerId)) {
				tempcustomerId=cust;
				itr.remove();
			}
		}
		return tempcustomerId;
	}

}
