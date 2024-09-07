package com.learn.webflux.week_9.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.webflux.week_9.dto.CustomerDTO;
import com.learn.webflux.week_9.model.Customer;
import com.learn.webflux.week_9.service.CustomerService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
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
		log.info("Start get customer Info. customerId: {}", id);
		return customerService.getById(id);
	}
	
	@GetMapping("/page/{page}")
	public Flux<CustomerDTO> getCustomerByPage(@PathVariable("page") Integer page){
	    return customerService.getCustomerByPage(page);
	}
		
	@PostMapping("")
	public Mono<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO){
		return customerService.createCustomerInfo(Mono.just(customerDTO));
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<CustomerDTO>> updateCustomer(@PathVariable("id") Integer custmerId, @RequestBody CustomerDTO customerDTO){
		log.info("Start Update Customer: {}", custmerId);
		return customerService.updateCustomerInfo(custmerId, customerDTO);
	}
	
	@DeleteMapping("{id}")
	public Mono<ResponseEntity<Void>> deleteCustomer(@PathVariable("id") Integer customerId){
		log.info("Start Deleting customerId = {}", customerId);
		return customerService.deleteCustomer(customerId)
				.filter(cust -> cust)
				.map(c -> ResponseEntity.ok().<Void>build())
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

}
