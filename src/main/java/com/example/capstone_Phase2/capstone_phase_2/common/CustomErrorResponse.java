package com.example.capstone_Phase2.capstone_phase_2.common;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class CustomErrorResponse {
	
	 private LocalDateTime  timestamp;
	    private String status;
	    private String errorCode;
	    private String  message;
		public CustomErrorResponse(String errorCode, String message) {
			super();
			this.errorCode = errorCode;
			this.message = message;
		}

}
