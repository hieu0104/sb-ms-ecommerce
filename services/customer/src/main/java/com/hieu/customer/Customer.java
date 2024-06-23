package com.hieu.customer;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Customer {
    @Id
    String id;
    String firstname;
    String lastname;
    String email;
    Address address;
}
