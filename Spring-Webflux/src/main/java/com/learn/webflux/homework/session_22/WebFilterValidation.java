package com.learn.webflux.homework.session_22;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class WebFilterValidation implements WebFilter{

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		
		new ValidateRole().validateRoleUser(exchange, chain);
	
		return chain.filter(exchange);
	}

}
