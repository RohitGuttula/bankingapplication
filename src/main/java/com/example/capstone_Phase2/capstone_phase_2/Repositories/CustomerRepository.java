package com.example.capstone_Phase2.capstone_phase_2.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.capstone_Phase2.capstone_phase_2.Entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer,Integer> {

}
