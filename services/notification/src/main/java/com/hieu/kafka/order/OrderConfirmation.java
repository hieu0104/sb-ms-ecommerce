package com.hieu.kafka.order;

import com.hieu.kafka.order.Customer;
import com.hieu.kafka.order.Product;
import com.hieu.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}
