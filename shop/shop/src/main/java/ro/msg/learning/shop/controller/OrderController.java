package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.OrderDtoView;
import ro.msg.learning.shop.dto.OrdersDto;
import ro.msg.learning.shop.dto.ProductOrderDto;
import ro.msg.learning.shop.entity.Order;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.service.CustomerService;
import ro.msg.learning.shop.service.OrderService;
import ro.msg.learning.shop.service.ProductService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/order")
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;
    private final CustomerService customerService;

    @GetMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<List<OrdersDto>> creteOrder(@RequestBody OrderDtoView orderDTOView) {
        Map<Product, Integer> productQuantity = orderDTOView.getProductsList().stream()
                .collect(Collectors.toMap(x -> productService.readById(x.getProductId()), ProductOrderDto::getQuantity));
        Order order = Order.builder().city(orderDTOView.getCity()).country(orderDTOView.getCountry()).createAt(orderDTOView.getTimestamp()).streetAddress(orderDTOView.getStreetAddress()).county(orderDTOView.getCounty()).customer(customerService.findById(1)).build();
        List<OrdersDto> ordersDtos = orderService.createOrder(productQuantity, order).stream()
                .map(o -> OrdersDto.builder().id(o.getId()).city(o.getCity()).country(o.getCountry()).county(o.getCounty()).streetAddress(o.getStreetAddress()).createAt(o.getCreateAt()).customerId(o.getCustomer().getId()).customerName(o.getCustomer().getFirstName() + o.getCustomer().getLastName()).locationId(o.getLocation().getId()).locationName(o.getLocation().getName()).build())
                .collect(Collectors.toList());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accepted", MediaType.APPLICATION_JSON_VALUE);
        return ResponseEntity.accepted().headers(headers).body(ordersDtos);
    }

}
