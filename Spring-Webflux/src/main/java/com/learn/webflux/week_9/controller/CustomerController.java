package com.learn.webflux.week_9.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.webflux.week_9.dto.CustomerDTO;
import com.learn.webflux.week_9.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("customers")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@GetMapping
	public Flux<CustomerDTO> getAll(){
		return customerService.getAll();
	}
	
	@GetMapping("get/{id}")
	public Mono<CustomerDTO> getById( @PathVariable("id") Integer id){
		return customerService.getById(id);
	}
	
	@GetMapping("/page/{page}")
	public Flux<CustomerDTO> getCustomerByPage(@PathVariable("page") Integer page){
	    return customerService.getCustomerByPage(page);
	}
		
	
	
	
}
