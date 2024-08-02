package com.learn.webflux.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.learn.webflux.dto.ProductDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/tranditional")
@Slf4j
public class TranditionalContoller {

	private final RestClient restClient = RestClient
			.builder()
			.baseUrl("http://localhost:8080")
			.build();
	
	@GetMapping("/products")
	public List<ProductDto> getProduct(){
		List<ProductDto> listProduct = this.restClient.get()
		.uri("/learn001/products/black")
		.retrieve()
		.body(new ParameterizedTypeReference<List<ProductDto>>() {
		})
		;
		log.info("Recieved response: {}"+listProduct);
		return listProduct;
	}
}
