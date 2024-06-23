package com.hieu.orderline;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderLineMapper {
    OrderLine toOrderLine(OrderLineRequest request);
    OrderLineRespone toOrderLineResponse(OrderLine orderLine);
}
