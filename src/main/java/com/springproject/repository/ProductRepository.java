package com.springproject.repository;

import com.springproject.model.ProductResponse;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepository {

    public List<ProductResponse> findAll(String tag) {
        List<ProductResponse> products = Arrays.asList(
                new ProductResponse(
                        "1",
                        "AMD Ryzen 9 5950X",
                        "Graphic card",
                        79900,
                        Arrays.asList("AMD", "Processor")),
                new ProductResponse(
                        "2",
                        "Intel Core i9-9900KF",
                        "Graphic card",
                        37900,
                        Arrays.asList("Intel", "Processor")
                )
        );

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
}
