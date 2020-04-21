package ro.msg.learning.shop.DTO.builder;

import ro.msg.learning.shop.DTO.OrdersDTO;
import ro.msg.learning.shop.entity.Orders;

public class OrderBuilder {
    public static OrdersDTO generateDTOFromEntity(Orders orders){
        return new OrdersDTO(orders.getId(),
                orders.getCreateAt(),
                orders.getCountry(),
                orders.getCity(),
                orders.getCounty(),
                orders.getStreetAddress(),
                LocationBuilder.generateDTOFromEntity(orders.getLocation()),
                CustomerBuilder.generateDTOFromEntity(orders.getCustomer()));
    }

    public static Orders generateEntityFromDTO(OrdersDTO ordersDTO){
        return new Orders(ordersDTO.getId(),
                ordersDTO.getCreateAt(),
                ordersDTO.getCountry(),
                ordersDTO.getCity(),
                ordersDTO.getCounty(),
                ordersDTO.getStreetAddress(),
                LocationBuilder.generateEntityFromDTO(ordersDTO.getLocationDTO()),
                CustomerBuilder.generateEntityFromDTO(ordersDTO.getCustomerDTO()));
    }

}
