package com.learn.webflux.week_9.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.learn.webflux.week_9.entity.Customer;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer>{

}
