package com.hieu.payment;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class PaymentController {
    PaymentService service;
    @PostMapping
    public ResponseEntity<Integer> create(
            @RequestBody @Valid PaymentRequest request
    ){
        return ResponseEntity.ok(service.create(request));
    }

}
