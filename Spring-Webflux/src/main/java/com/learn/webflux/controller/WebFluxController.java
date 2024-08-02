package com.learn.webflux.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.learn.webflux.dto.ProductDto;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/webflux")
@Slf4j
public class WebFluxController {
	private WebClient webClient = WebClient
			.builder()
			.baseUrl("http://localhost:8080")
			.build();
	
	@GetMapping(value = "/products", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ProductDto> getProduct(){
		 return webClient
				.get()
				.uri("/learn001/products/black")
				.retrieve()
				.bodyToFlux(ProductDto.class)
				.doOnNext(x -> log.info("Retrieved : "+x))
				;
		
	}

}
