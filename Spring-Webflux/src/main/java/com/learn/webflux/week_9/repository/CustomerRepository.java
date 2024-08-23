package com.learn.webflux.week_9.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.learn.webflux.week_9.model.Customer;

import reactor.core.publisher.Flux;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer>{
	
	Flux<Customer> findBy(Pageable pageable);

}
