package com.example.capstone_Phase2.capstone_phase_2.DAO;

import java.util.List;

import com.example.capstone_Phase2.capstone_phase_2.Entity.Customer;



public interface CustomerDAO {
	public Customer CreateCustomer(Customer customer);

	public Customer GetCustomerByID(Integer customerId);

	public List<Customer> GetAllCustomers();

	//public Customer updateCustomer(Long customerId, Customer customer);

	public Customer deleteCustomer(Integer customerId);

	public Customer updateCustomer(Integer customerId, Customer customer);

}
