package com.example.capstone_Phase2.capstone_phase_2.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) 
public class CustNotFound extends RuntimeException {
	
	public CustNotFound(String message) {
		super(message);
	}

}
