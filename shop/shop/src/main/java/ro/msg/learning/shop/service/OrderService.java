package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.strategy.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final LocationStrategy createOrderStrategy;
    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;
    private final StockService stockService;

    @Transactional
    public List<Order> createOrder(Map<Product, Integer> productQuantity, Order order) {
        List<Order> orders = new ArrayList<>();
        Map<Location, Map<Product, Integer>> locationProductQuantity = createOrderStrategy.getLocation(productQuantity);
        for (Map.Entry<Location, Map<Product, Integer>> locProdQuan : locationProductQuantity.entrySet()) {
            Order orderForSave = Order.builder().city(order.getCity()).country(order.getCountry()).createAt(order.getCreateAt()).streetAddress(order.getStreetAddress()).county(order.getCounty()).customer(order.getCustomer()).location(locProdQuan.getKey()).build();
            Order savedOrder = orderRepository.save(orderForSave);
            orders.add(savedOrder);
            for (Map.Entry<Product, Integer> productQuantityEntry : locProdQuan.getValue().entrySet()) {
                OrderDetail orderDetail = OrderDetail.builder().order(orderForSave).orderId(orderForSave.getId()).productId(productQuantityEntry.getKey().getId()).quantity(productQuantityEntry.getValue()).product(productQuantityEntry.getKey()).build();
                orderDetailService.save(orderDetail);
                Optional<Stock> stockForUpdate = stockService.findStockByLocationAndProduct(locProdQuan.getKey(), productQuantityEntry.getKey());
                stockForUpdate.ifPresent(stock -> stock.setQuantity(stock.getQuantity() - productQuantityEntry.getValue()));
            }
        }
        return orders;
    }

    public void deleteAll() {
        orderRepository.deleteAll();
    }
}
