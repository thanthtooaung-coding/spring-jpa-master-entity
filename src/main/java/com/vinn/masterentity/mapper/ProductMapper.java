package com.vinn.masterentity.mapper;

import com.vinn.masterentity.entity.Product;
import com.vinn.masterentity.response.ProductRequest;
import com.vinn.masterentity.response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequest request) {
        if (request == null) {
            return null;
        }
        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());
        return product;
    }

    public ProductResponse toResponse(Product entity) {
        if (entity == null) {
            return null;
        }
        return new ProductResponse(
            entity.getId(),
            entity.getName(),
            entity.getPrice(),
            entity.getStatus(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }

    public void updateEntityFromRequest(ProductRequest request, Product entity) {
        if (request == null || entity == null) {
            return;
        }
        entity.setName(request.name());
        entity.setPrice(request.price());
        if (request.status() != null) {
            entity.setStatus(request.status());
        }
    }
}
