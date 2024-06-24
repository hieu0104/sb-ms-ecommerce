package com.hieu.payment;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    Payment toPayment (PaymentRequest request);
    PaymentResponse toPaymentResponse(Payment payment);
}
