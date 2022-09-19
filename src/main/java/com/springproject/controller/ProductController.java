package com.springproject.controller;


import com.springproject.entity.ProductEntity;
import com.springproject.exceptions.IdNotFoundException;
import com.springproject.model.CreateProductRequest;
import com.springproject.model.ProductResponse;
import com.springproject.model.ProductUpdateRequest;
import com.springproject.repository.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @GetMapping("/products")
    public List<ProductResponse> getAllProducts(@RequestParam(required = false) String tag) {
        return productRepository.findAll().stream()
                .filter(productEntity -> productEntity.getTags().contains(tag))
                .map(productEntity -> mapToResponce(productEntity))
                .collect(Collectors.toList());
    }

    @GetMapping("/products/{id}")
    public ProductResponse getProductById(@PathVariable String id) {
        ProductEntity product = productRepository.getReferenceById(id);
        return mapToResponce(product);
    }

    @NotNull
    private ProductResponse mapToResponce(ProductEntity product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPriceInCent(),
                product.getTags()
        );
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id) {
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/products")
    public ProductResponse createProduct(@RequestBody CreateProductRequest request) {
        ProductEntity productEntity = new ProductEntity(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getDescription(),
                request.getPriceInCent(),
                request.getTags()
        );
        ProductEntity savedProduct = productRepository.save(productEntity);
        return mapToResponce(savedProduct);
    }

    @PutMapping("/products/{id}")
    public ProductResponse updateProduct(@RequestBody ProductUpdateRequest request, @PathVariable String id) {
        final ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Product with the id " + id + " not found.",
                        HttpStatus.BAD_REQUEST)
                );
        final ProductEntity updatedProduct = new ProductEntity(
                product.getId(),
                request.getName() == null ? product.getName() : request.getName(),
                product.getDescription() == null ? product.getDescription() : request.getDescription(),
                product.getPriceInCent() == null ? product.getPriceInCent() : request.getPriceInCent(),
                product.getTags()
                );

        ProductEntity savedProduct = productRepository.save(updatedProduct);
        return mapToResponce(savedProduct);
    }
}
