package com.learn.webflux.week_9.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.learn.webflux.week_9.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer>{
	
	Flux<Customer> findBy(Pageable pageable);

	@Modifying
	@Query("DELETE FROM customer where id = :customerId")
	Mono<Boolean> deleteCustomerById(Integer customerId);



}
