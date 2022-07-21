package com.example.capstone_Phase2.capstone_phase_2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.capstone_Phase2.capstone_phase_2.DAO.CustomerDAO;
import com.example.capstone_Phase2.capstone_phase_2.Entity.Customer;
import com.example.capstone_Phase2.capstone_phase_2.service.CustomerService;



@RestController
public class CustomerController {
	
	@Autowired
	CustomerService service;
	@PostMapping("/api/customers")
	public ResponseEntity<Customer> CreateCustomer(@RequestBody Customer customer) {
		Customer cust= service.CreateCustomer(customer);
		return new ResponseEntity<>(cust,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/api/customers/{customerId}")
	public Customer GetCustomerByID(@PathVariable Integer customerId) {
		Customer customer=service.GetCustomerByID(customerId);
		return customer;
	}
	
	@GetMapping("/api/customers")
	public List<Customer> GetAllCustomers(){
		List<Customer> customers=service.GetAllCustomers();
		return customers;
		
	}
	
	@PutMapping("/api/customers/{customerId}")
	public Customer updateCustomer(@PathVariable Integer customerId,@RequestBody Customer customer) {
		Customer updateCustomer=service.updateCustomer(customer, customerId);
		return updateCustomer;
		
	}
	
	@DeleteMapping("/api/customers/{customerId}")
	public Customer deleteCustomer(@PathVariable Integer customerId) {
		Customer deletedCustomer=service.deleteCustomer(customerId);
		return deletedCustomer;
	}
	
	

}
