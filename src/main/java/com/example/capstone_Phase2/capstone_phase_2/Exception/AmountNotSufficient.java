package com.example.capstone_Phase2.capstone_phase_2.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AmountNotSufficient extends RuntimeException {
	
	public AmountNotSufficient(String message) {
		super(message);
	}

}
