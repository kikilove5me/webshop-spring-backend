package com.springproject.service;

import com.springproject.model.OrderPositionResponse;
import com.springproject.model.ProductCreateRequest;
import com.springproject.model.ProductResponse;
import com.springproject.repository.OrderPositionRepository;
import com.springproject.repository.OrderRepository;
import com.springproject.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
public class ShoppingCartServiceTest {
    private ProductRepository productRepository;
    private ShoppingCartService service;

    @BeforeEach
    public void setupTests() {
        productRepository = new ProductRepository();
        service = new ShoppingCartService(
                new OrderRepository(),
                new OrderPositionRepository(),
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
        ProductResponse saveProduct = getProductResponse(1000);
        //given
        List<OrderPositionResponse> orderPositions = new ArrayList<>();
        addOrderPosition(saveProduct, orderPositions, 1);
        //when
        Long result = service.getTotalCart(orderPositions, 500);
        //then
        assertThat(result).isEqualTo(1500);
    }

    private void addOrderPosition(ProductResponse saveProduct, List<OrderPositionResponse> orderPositions, int quantity) {
        orderPositions.add(new OrderPositionResponse(
                "1",
                "order-id",
                saveProduct.getId(),
                quantity));
    }

    private ProductResponse getProductResponse(long price) {
        ProductResponse saveProduct = productRepository.save(new ProductCreateRequest(
                "",
                "",
                price,
                new ArrayList<>())
        );
        return saveProduct;
    }
}
