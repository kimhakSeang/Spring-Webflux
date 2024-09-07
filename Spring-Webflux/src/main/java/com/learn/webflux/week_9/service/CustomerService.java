package com.learn.webflux.week_9.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learn.webflux.week_9.dto.CustomerDTO;
import com.learn.webflux.week_9.mapper.CustomerMapper;
import com.learn.webflux.week_9.model.Customer;
import com.learn.webflux.week_9.repository.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {
	private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CustomerMapper customerMapper;
	
	public Flux<CustomerDTO> getAll(){
		return customerRepository.findAll()
				.map(cst -> customerMapper.toCustomerDTO(cst));
	}
	
	public Mono<CustomerDTO> getById(Integer customerId){
		return customerRepository.findById(customerId)
				.map(customerMapper::toCustomerDTO);
	}
	
	public Flux<CustomerDTO> getCustomerByPage(Integer page){
		PageRequest pageRequest = PageRequest.of(page, 3).withSort(Sort.by("name").descending());
		return customerRepository.findBy(pageRequest)
				.map(customerMapper::toCustomerDTO);
	}
	
	public Mono<CustomerDTO> createCustomerInfo(Mono<CustomerDTO> customerDTO){
        return customerDTO.map(customerMapper::toCustomer)
        		.doOnNext(customerRepository::save)
        		.flatMap(customerRepository::save)
//        		.customerRepository.save(customer)
				.map(customerMapper::toCustomerDTO);
	}
	public Mono<ResponseEntity<CustomerDTO>> updateCustomerInfo(Integer customerId, CustomerDTO customerDTO){
		return customerRepository.findById(customerId)
				.switchIfEmpty(Mono.error(new RuntimeException("Not found")))
				.doOnNext(cst -> log.info("Customer:{}", cst))
				.doOnNext(cust -> {
					cust.setId(customerId);
					cust.setName(customerDTO.getName());
					cust.setEmail(customerDTO.getEmail());
				})
				.flatMap(customerRepository::save)
				.map(customerMapper::toCustomerDTO)
				.map(c->ResponseEntity.ok(c))
				;
//				.map(cust -> customerMapper.toCustomerDTO(customerRepository.findById(customerId).blockOptional().get()));
	}
	public Mono<Boolean> deleteCustomer(Integer customerId){
		return customerRepository.deleteCustomerById(customerId);
//		return customerRepository.deleteById(customerId).hasElement();


//		return customerRepository.findById(customerId)
//				.switchIfEmpty(Mono.error( new RuntimeException()))
//				.flatMap(cust -> customerRepository.deleteById(customerId))
//				.map(customerMapper::toCustomerDTO)
//				;
	}
}
