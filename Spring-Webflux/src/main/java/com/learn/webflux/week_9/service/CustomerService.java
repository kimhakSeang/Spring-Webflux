package com.learn.webflux.week_9.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.learn.webflux.week_9.dto.CustomerDTO;
import com.learn.webflux.week_9.mapper.CustomerMapper;
import com.learn.webflux.week_9.repository.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CustomerMapper customerMapper;
	
	public Flux<CustomerDTO> getAll(){
		return customerRepository.findAll()
				.map(cst -> customerMapper.toCustomerDTO(cst));
	}
	
	public Flux<CustomerDTO> getCustomers(int pageNumber, int pageSize){
		return null;
	}
	
	public Mono<Page<CustomerDTO>> getCustomers2(int pageNumber, int pageSize){
		return null;
	}
		
	
	public Mono<CustomerDTO> getById(Integer customerId){
		return customerRepository.findById(customerId)
				.map(customerMapper::toCustomerDTO);
	}
	
}
