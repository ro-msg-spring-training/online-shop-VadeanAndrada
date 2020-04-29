package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.OrderDetail;
import ro.msg.learning.shop.repository.OrderDetailRepository;


@Service
@RequiredArgsConstructor
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    public void save(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

    public void deleteAll() {
        orderDetailRepository.deleteAll();
    }

}
