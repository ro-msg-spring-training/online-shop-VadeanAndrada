package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.OrderDtoView;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.service.OrderService;
import ro.msg.learning.shop.service.ProductService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;

    @GetMapping("/orders")
    @Transactional
    public void stockDTOS(@RequestBody OrderDtoView orderDTOView){
        List<Stock> stocks = orderDTOView.getProductsList().stream()
                                        .map(s -> Stock.builder().product(productService.readById(s.getProductId()))
                                                                .quantity(s.getQuantity())
                                                                .build())
                                        .collect(Collectors.toList());

        orderService.getProducts(stocks);
    }

}
