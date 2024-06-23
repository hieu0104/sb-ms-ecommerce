package com.hieu.category;

import com.hieu.product.Product;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Category {
    @Id
    @GeneratedValue
    Integer id;
    String name;
    String description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    List<Product> products;

}
