package com.weblux.app.dto;

import lombok.Data;

@Data
public class ResponseDto {
	private String code;
	private String message;
	
	public ResponseDto(String code, String message) {
		this.code = code;
		this.message = message;
	}
}