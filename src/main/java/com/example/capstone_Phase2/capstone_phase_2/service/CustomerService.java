package com.example.capstone_Phase2.capstone_phase_2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.capstone_Phase2.capstone_phase_2.Entity.Customer;
import com.example.capstone_Phase2.capstone_phase_2.Exception.CustNotFound;
import com.example.capstone_Phase2.capstone_phase_2.Exception.CustomerNotFound;
import com.example.capstone_Phase2.capstone_phase_2.Exception.EmailalreadyPresent;
import com.example.capstone_Phase2.capstone_phase_2.Repositories.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository repository;
	public Customer CreateCustomer(Customer customer){
		Customer createdCustomer=null;
		int count=0;
		List<Customer> customers=(List<Customer>) repository.findAll();
		for(Customer cust: customers) {
			if(cust.getEmail().equals(customer.getEmail())) {
				count=count+1;
			}
			}
		if(count>0) {
			throw new EmailalreadyPresent("Customer with email: "+customer.getEmail()+" already Present"); 
		}
	createdCustomer=repository.save(customer);
		return createdCustomer;
	}
    public Customer GetCustomerByID(Integer custid) {
    	Optional<Customer> customers=repository.findById(custid);
    	if(customers.isEmpty()) {
    		throw new CustNotFound("Customer With ->"+custid+" Not found");
    	}
    	Customer customer = customers.get();
    	if(customer==null) {
    		throw new CustNotFound("Customer With ->"+custid+" Not found");
    	}
    	return customer;
    }
     
    public List<Customer> GetAllCustomers(){
    	List<Customer> customers= (List<Customer>) repository.findAll();
    	return customers;
    }
    
    public Customer updateCustomer(Customer customer,int custid) {
    	Optional<Customer> customers=repository.findById(custid);
    	if(customers.isEmpty()) {
    		throw new CustNotFound("Customer with ->"+custid+"Not Found");
    	}
    	Customer cust=customers.get();
    	if(cust==null) {
    		throw new CustNotFound("Customer with"+custid+"Not Found");
    	}
    	cust.setEmail(customer.getEmail());
    	cust.setFirst_Name(customer.getFirst_Name());
    	cust.setLast_Name(customer.getLast_Name());
    	Customer updatedCustomer=repository.save(cust);
    	return updatedCustomer;
    }
    public Customer deleteCustomer(int custid) {
    	Optional<Customer> customers=repository.findById(custid);
    	if(customers.isEmpty()) {
    		throw new CustNotFound("Customer with"+custid+"Not Found");
    	}
    	Customer cust=customers.get();
    	if(cust==null) {
    		throw new CustNotFound("Customer with"+custid+"Not Found");
    	}
    	repository.deleteById(custid);
    	return cust;
    	
    }
    
}
