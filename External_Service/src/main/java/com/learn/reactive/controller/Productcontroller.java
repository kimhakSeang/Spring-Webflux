package com.learn.reactive.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.reactive.dto.Product;
import com.learn.reactive.service.Productservice;
import com.learn.reactive.service.util.Transformer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("learn001")
@RequiredArgsConstructor
public class Productcontroller {

	
	private final Productservice productservice;
	private String name;
	
	@GetMapping("/products")
	@Operation(summary = "Product Service", description = "Produce 10 items, 1 item per 1s"
	, tags = {"Learn 0001"}, parameters = {
            @Parameter(name = "id", description = "id of product", required = true)
	})
	public Flux<Product> getProduct(){
		String path = "learn001/products";
		return Flux.from(productservice.getProducts())
				   .transform(Transformer.fluxTracking(path));
	}
	
	@GetMapping("/products/black")
	@Operation(summary = "Product Service", description = "Produce 10 items, 1 item per 1s"
	, tags = {"Learn 0001"}, parameters = {
            @Parameter(name = "id", description = "id of product", required = true)
	})
	public Flux<Product> getProductError(){
		String path = "learn001/products";
		return Flux.from(productservice.getProductsError())
				   .transform(Transformer.fluxTracking(path));
	}
}
