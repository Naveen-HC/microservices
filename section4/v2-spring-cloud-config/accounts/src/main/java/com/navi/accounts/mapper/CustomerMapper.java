package com.navi.accounts.mapper;

import com.navi.accounts.dto.CustomerDto;
import com.navi.accounts.entity.Customer;

public class CustomerMapper {

    public static Customer mapCustomer(CustomerDto customerDto, Customer customer){
        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }

    public static CustomerDto mapCustomerDto(Customer customer, CustomerDto customerDto){
        customerDto.setEmail(customer.getEmail());
        customerDto.setName(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }
}
