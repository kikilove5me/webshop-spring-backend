package com.springproject.service;

import com.springproject.entity.ProductEntity;
import com.springproject.model.*;
import com.springproject.repository.OrderPositionRepository;
import com.springproject.repository.OrderRepository;
import com.springproject.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ShoppingCartServiceTest {
    private ProductRepository productRepository;
    private ShoppingCartService service;

//    class OrderRepositoryMock extends OrderRepository {
//        public OrderResponse save(OrderCreateRequest request) {
//            return null;
//        }
//    }

    @BeforeEach
    public void setupTests() {
        productRepository = mock(ProductRepository.class);
        service = new ShoppingCartService(
                mock(OrderRepository.class),
                mock(OrderPositionRepository.class),
                productRepository
        );
    }
    @Test
    public void testgetoTotalCartEmpty() {
        //given

        //when
        Long result = service.getTotalCart(
                new ArrayList<OrderPositionResponse>(),
                500
        );
        //then
        assertThat(result).isEqualTo(500);
    }

    @Test
    public void testGetTotalCartOneProduct() {
        ProductEntity saveProduct = new ProductEntity(
                UUID.randomUUID().toString(),
                "",
                "",
                1000,
                new ArrayList<>());
        //given
        given(productRepository.findById(any())).willReturn(java.util.Optional.of(saveProduct));
        List<OrderPositionResponse> orderPositions = new ArrayList<>();
        addOrderPosition(saveProduct, orderPositions, 1);
        //when
        Long result = service.getTotalCart(orderPositions, 500);
        //then
        assertThat(result).isEqualTo(1500);
    }

    private void addOrderPosition(ProductEntity saveProduct, List<OrderPositionResponse> orderPositions, int quantity) {
        orderPositions.add(new OrderPositionResponse(
                "1",
                "order-id",
                saveProduct.getId(),
                quantity));
    }
}
