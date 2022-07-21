package com.example.capstone_Phase2.capstone_phase_2.common;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.capstone_Phase2.capstone_phase_2.Exception.AccountNotFound;
import com.example.capstone_Phase2.capstone_phase_2.Exception.AmountNotSufficient;
import com.example.capstone_Phase2.capstone_phase_2.Exception.CustNotFound;
import com.example.capstone_Phase2.capstone_phase_2.Exception.CustomerNotFound;
import com.example.capstone_Phase2.capstone_phase_2.Exception.EmailalreadyPresent;
import com.example.capstone_Phase2.capstone_phase_2.Exception.Negativeamount;
import com.example.capstone_Phase2.capstone_phase_2.Exception.SameAccountNumbers;


@RestControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler(AccountNotFound.class)
	public ResponseEntity<CustomErrorResponse>handleAccountNotFoundException(AccountNotFound a){
		CustomErrorResponse Error=new CustomErrorResponse("Not Found Exception",a.getMessage());
		Error.setTimestamp(LocalDateTime.now());
		Error.setStatus(HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity<CustomErrorResponse>(Error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustNotFound.class)
	public ResponseEntity<CustomErrorResponse>handleCustomerNotFoundException(CustNotFound c){
		CustomErrorResponse Error=new CustomErrorResponse("Not Found Exception", c.getMessage());
		Error.setTimestamp(LocalDateTime.now());
		Error.setStatus(HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity<CustomErrorResponse>(Error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmailalreadyPresent.class)
	public ResponseEntity<CustomErrorResponse>handleEmailalreadyPresentException(EmailalreadyPresent e){
		CustomErrorResponse Error=new CustomErrorResponse("Bad Request Exception",e.getMessage());
		Error.setTimestamp(LocalDateTime.now());
		Error.setStatus(HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<CustomErrorResponse>(Error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AmountNotSufficient.class)
	public ResponseEntity<CustomErrorResponse>handleAmountNotSufficientException(AmountNotSufficient a){
		CustomErrorResponse Error=new CustomErrorResponse("Bad Request Exception",a.getMessage());
		Error.setTimestamp(LocalDateTime.now());
		Error.setStatus(HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<CustomErrorResponse>(Error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(SameAccountNumbers.class)
	public ResponseEntity<CustomErrorResponse>handleSameAccountNumberException(SameAccountNumbers s){
		CustomErrorResponse Error=new CustomErrorResponse("Bad Request Exception",s.getMessage());
		Error.setTimestamp(LocalDateTime.now());
		Error.setStatus(HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<CustomErrorResponse>(Error,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Negativeamount.class)
	public ResponseEntity<CustomErrorResponse>handleNegativeamountException(Negativeamount n){
		CustomErrorResponse Error=new CustomErrorResponse("Bad Request Exception", n.getMessage());
		Error.setTimestamp(LocalDateTime.now());
		Error.setStatus(HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<CustomErrorResponse>(Error,HttpStatus.BAD_REQUEST);
		
	}

}
