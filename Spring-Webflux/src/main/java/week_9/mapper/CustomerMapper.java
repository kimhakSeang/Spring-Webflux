package week_9.mapper;

import org.springframework.stereotype.Component;

import week_9.dto.CustomerDTO;
import week_9.entity.Customer;

@Component
public class CustomerMapper {
	
	public Customer toCustomer(CustomerDTO dto) {
		Customer customer = new Customer();
		customer.setName(dto.getName());
		customer.setEmail(dto.getEmail());
		return customer;
	}
	
	public CustomerDTO toCustomerDTO(Customer customer) {
		CustomerDTO dto = new CustomerDTO();
		dto.setId(customer.getId());
		dto.setName(customer.getName());
		dto.setEmail(customer.getEmail());
		return dto;
	}
}
