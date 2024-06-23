package com.hieu.customer;

import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface CustomerMapper {
Customer toCustomer(CustomerRequest request);
CustomerResponse toCustomerResponse(Customer customer);
}
