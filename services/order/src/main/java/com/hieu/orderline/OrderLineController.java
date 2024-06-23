package com.hieu.orderline;

import com.hieu.order.OrderRequest;
import com.hieu.order.OrderResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class OrderLineController {
    OrderLineService service;

    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(
            @PathVariable("order-id")
            Integer orderId) {
        return ResponseEntity.ok(service.findALLByOrderId(orderId));
    }
}
