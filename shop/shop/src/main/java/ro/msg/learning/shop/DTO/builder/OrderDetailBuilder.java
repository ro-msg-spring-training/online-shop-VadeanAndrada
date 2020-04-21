package ro.msg.learning.shop.DTO.builder;

import ro.msg.learning.shop.DTO.OrderDetailDTO;
import ro.msg.learning.shop.entity.OrderDetail;

public class OrderDetailBuilder {
    public static OrderDetail generateEntityFromDTO (OrderDetailDTO orderDetailDTO){
        return new OrderDetail(OrderBuilder.generateEntityFromDTO(orderDetailDTO.getOrdersDTO()),
                ProductBuilder.generateEntityFromDTO(orderDetailDTO.getProductDTO()),
                orderDetailDTO.getQuantity());
    }

    public static OrderDetailDTO generateDTOFromEntity (OrderDetail orderDetail){
        return new OrderDetailDTO(OrderBuilder.generateDTOFromEntity(orderDetail.getOrders()),
                ProductBuilder.generateDTOFromEntity(orderDetail.getProduct()),
                orderDetail.getQuantity());
    }
}
