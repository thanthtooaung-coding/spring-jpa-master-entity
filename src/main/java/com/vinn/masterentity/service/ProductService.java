package com.vinn.masterentity.service;

import com.vinn.masterentity.entity.Product;
import com.vinn.masterentity.mapper.ProductMapper;
import com.vinn.masterentity.repository.ProductRepository;
import com.vinn.masterentity.response.ProductRequest;
import com.vinn.masterentity.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<ProductResponse> getProductById(Long id) {
        return productRepository.findById(id).map(productMapper::toResponse);
    }

    public ProductResponse createProduct(ProductRequest request) {
        Product product = productMapper.toEntity(request);
        Product savedProduct = productRepository.save(product);
        return productMapper.toResponse(savedProduct);
    }

    @Transactional
    public Optional<ProductResponse> updateProduct(Long id, ProductRequest request) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    productMapper.updateEntityFromRequest(request, existingProduct);
                    Product updatedProduct = productRepository.save(existingProduct);
                    return productMapper.toResponse(updatedProduct);
                });
    }

    @Transactional
    public boolean deleteProduct(Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.delete();
                    productRepository.save(product);
                    return true;
                }).orElse(false);
    }
}
