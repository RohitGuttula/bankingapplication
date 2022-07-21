package com.example.capstone_Phase2.capstone_phase_2.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SameAccountNumbers extends RuntimeException {
	
	public SameAccountNumbers(String message) {
		super(message);
	}

}
