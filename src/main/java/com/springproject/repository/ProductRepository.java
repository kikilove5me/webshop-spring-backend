package com.springproject.repository;

import com.springproject.model.ProductCreateRequest;
import com.springproject.model.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class ProductRepository {
    List<ProductResponse> products = new ArrayList<>();
    public ProductRepository() {
        products.add(new ProductResponse(
                "1",
                "AMD Ryzen 9 5950X",
                "Graphic card",
                79900,
                Arrays.asList("AMD", "Processor")
                ));
        products.add(new ProductResponse(
                "2",
                "Intel Core i9-9900KF",
                "Graphic card",
                37900,
                Arrays.asList("Intel", "Processor")
        ));
    }

    public List<ProductResponse> findAll(String tag) {
        if (tag == null)
            return products;

        return filter(products, tag);
    }

    private static List<ProductResponse> filter(List<ProductResponse> l, String tag) {
        return l.stream()
                .filter(p -> p.getTags()
                        .stream()
                        .map(String::toLowerCase)
                        .collect(Collectors.toList())
                        .contains(tag.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Optional<ProductResponse> findByID(String id) {
        Optional<ProductResponse> product = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        return product;
    }

    public void deleteByID(String id) {
        this.products = products.stream()
                .dropWhile(p -> p.getId().equals(id))
                .collect(Collectors.toList());
    }

    public ProductResponse save(ProductCreateRequest request) {
        ProductResponse response = new ProductResponse(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getDescription(),
                request.getPriceInCent(),
                request.getTags()
        );
        products.add(response);
        return response;
    }
}
