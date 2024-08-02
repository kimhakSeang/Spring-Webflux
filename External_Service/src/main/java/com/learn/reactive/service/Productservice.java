package com.learn.reactive.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.learn.reactive.dto.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class Productservice {

	public Flux<Product> getProducts(){
		return Flux.range(1, 10)
				.delayElements(Duration.ofSeconds(1))
				.map(x-> new Product(x, "Product_"+x, x));
	}
	public Flux<Product> getProductsError(){
		return Flux.range(1, 10)
				.take(5)
				.delayElements(Duration.ofSeconds(1))
				.map(x-> new Product(x, "Product_"+x, x))
				.concatWith(Mono.fromRunnable(()->System.exit(1)))
				;
	}
}
