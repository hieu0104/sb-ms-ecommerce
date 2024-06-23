package com.hieu.orderline;

import com.hieu.order.OrderRequest;
import com.hieu.order.OrderResponse;
import com.hieu.order.OrderService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class OrderLIneController {
    OrderService service;

    @PostMapping
    public ResponseEntity<Integer> create(
            @RequestBody
            @Valid
            OrderRequest request
    ) {
        return ResponseEntity.ok(service.createOrder(request));
    }


    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

//    @GetMapping("/exists/{customer-id}")
//    public ResponseEntity<Boolean> existById(
//            @PathVariable("customer-id") String customerId
//    ) {
//        return ResponseEntity.ok(this.service.existsById(customerId));
//    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findById(
            @PathVariable("order-id") Integer orderId

    ) {
        return ResponseEntity.ok(service.findById(orderId));
    }
//
//    @DeleteMapping("/{order-id}")
//    public ResponseEntity<Void> delete(
//            @PathVariable("order-id") String orderId
//    ) {
//        this.service.delete(orderId);
//        return ResponseEntity.accepted().build();
//    }
}
