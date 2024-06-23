package com.hieu.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface ProductMapper {
Product toProduct(ProductRequest request);
ProductResponse toProductResponse(Product product);
    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "description", source = "product.description")
    @Mapping(target = "price", source = "product.price")
    ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity);
}
