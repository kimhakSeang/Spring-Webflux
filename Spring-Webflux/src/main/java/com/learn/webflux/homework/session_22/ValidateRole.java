package com.learn.webflux.homework.session_22;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

public class ValidateRole {
	
	
	public Mono<?> validateRoleUser(ServerWebExchange exchange, WebFilterChain chain){
		
		HttpMethod method = exchange.getRequest().getMethod();
		boolean isGet = HttpMethod.GET.equals(method);
		
		if(isGet) {
			return chain.filter(exchange);
		}
		return Mono.fromRunnable(() -> exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN));
	}

}
