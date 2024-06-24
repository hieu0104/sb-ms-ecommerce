package com.hieu.notification;

import com.hieu.kafka.order.OrderConfirmation;
import com.hieu.kafka.payment.PaymentConfirmation;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Notification {
    @Id
    String id;
    NotificationType type;
    LocalDateTime notificationDate;
    OrderConfirmation orderConfirmation;
    PaymentConfirmation paymentConfirmation;

}
