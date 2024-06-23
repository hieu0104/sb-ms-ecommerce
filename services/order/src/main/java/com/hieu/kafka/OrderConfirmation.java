package com.hieu.kafka;

import com.hieu.customer.CustomerResponse;
import com.hieu.order.PaymentMethod;
import com.hieu.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;
public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}