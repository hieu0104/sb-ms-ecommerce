package com.hieu.orderline;

import com.hieu.order.OrderRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderLineService {
    OrderLineRepository repository;
    OrderLineMapper mapper;
    public Integer saveOrderLine(OrderLineRequest request) {
        var order = mapper.toOrderLine(request);
     return repository.save(order).getId();
    }


    public List<OrderLineResponse> findALLByOrderId(Integer orderId) {
        return repository.findAllByOrderId(orderId)
                .stream()
                .map(mapper::toOrderLineResponse)
                .toList();
    }
}
