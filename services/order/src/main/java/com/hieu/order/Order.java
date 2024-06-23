package com.hieu.order;

import com.hieu.orderline.OrderLine;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "customer_order")

public class Order {

    @Id
    @GeneratedValue
    Integer id;

    @Column(unique = true, nullable = false)
    String reference;

    BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;

    String customerId;

    @OneToMany(mappedBy = "order")
    List<OrderLine> orderLines;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    LocalDateTime lastModifiedDate;
}
