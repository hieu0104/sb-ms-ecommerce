package com.hieu.order;

import com.hieu.customer.CustomerResponse;
import org.mapstruct.Mapper;
import org.springframework.jmx.export.annotation.ManagedOperation;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toOrder(OrderRequest request);
    OrderResponse toOrderResponse(Order order);
}
