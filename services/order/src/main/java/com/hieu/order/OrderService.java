package com.hieu.order;

import com.hieu.customer.CustomerClient;
import com.hieu.customer.CustomerResponse;
import com.hieu.exception.BusinessException;
import com.hieu.kafka.OrderConfirmation;
import com.hieu.kafka.OrderProducer;
import com.hieu.orderline.OrderLineRequest;
import com.hieu.orderline.OrderLineService;
import com.hieu.payment.PaymentRequest;
import com.hieu.product.ProductClient;
import com.hieu.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
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
public class OrderService {
    OrderRepository repository;
    CustomerClient customerClient;
    ProductClient productClient;
    OrderMapper mapper;
    OrderLineService orderLineService;
    OrderProducer orderProducer;

    public Integer createOrder(OrderRequest request) {
        //check the customer -> openfeign
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(
                        () -> new BusinessException("Cannot create order:: No Customer exist")
                );
        var purchaseProducts = productClient.purchaseProducts(request.products());

        //purchase the products -> product-ms RestTemplate
        this.productClient.purchaseProducts(request.products());

        //persist order
        var order = this.repository.save(mapper.toOrder(request));

        //persist order lines
        for (
                PurchaseRequest purchaseRequest : request.products()
        ) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );

        }

        //  start payment process
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );

        //send the order confirmation -> notification-ms (kafka)
        orderProducer.sendOrderConfirmationMessage(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchaseProducts
                )
        );
        return null;
    }


    public List<OrderResponse> findAll() {
        List<Order> orders = repository.findAll();

        return orders.stream()
                .map(
                        mapper::toOrderResponse
                )
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer id) {
        return repository.findById(id).map(mapper::toOrderResponse)
                .orElseThrow(
                        () ->  new EntityNotFoundException(
                                String.format("No order found with the provided ID: %d", id))
                );
    }
//
//    public void delete(String id) {
//        repository.delete(id);
//    }
}
