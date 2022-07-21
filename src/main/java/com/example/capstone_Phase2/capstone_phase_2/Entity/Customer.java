package com.example.capstone_Phase2.capstone_phase_2.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cust_id;
	private String first_Name;
	private String last_Name;
	private String email;
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="customer_accounts",
	joinColumns = @JoinColumn(name="cust_id",referencedColumnName="cust_id"),
	inverseJoinColumns= @JoinColumn(name="account_Number",referencedColumnName="account_Number")
	  )
	private Set<Account> accounts=new HashSet<>();
	public Customer(Integer cust_id, String first_Name, String last_Name, String email) {
		super();
		this.cust_id = cust_id;
		this.first_Name = first_Name;
		this.last_Name = last_Name;
		this.email = email;
	}
	 

}

