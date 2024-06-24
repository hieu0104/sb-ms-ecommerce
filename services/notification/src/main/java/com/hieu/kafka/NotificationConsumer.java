package com.hieu.kafka;

import com.hieu.email.EmailService;
import com.hieu.kafka.order.OrderConfirmation;
import com.hieu.kafka.payment.PaymentConfirmation;
import com.hieu.notification.Notification;
import com.hieu.notification.NotificationRepository;
import com.hieu.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.hieu.notification.NotificationType.ORDER_CONFIRMATION;
import static com.hieu.notification.NotificationType.PAYMENT_CONFIRMATION;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class NotificationConsumer {
    NotificationRepository repository;
    EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation));
        repository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );
        var customerName = paymentConfirmation.customerFirstname() + " " +
                paymentConfirmation.customerLastname();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(format("Consuming the message from payment-topic Topic:: %s", orderConfirmation));
        repository.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );
        var customerName = orderConfirmation.customer().firstname() + " " +
                orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
           orderConfirmation.customer().email(),
           customerName,
           orderConfirmation.totalAmount(),
           orderConfirmation.orderReference(),
           orderConfirmation.products()
        );
    }
}
