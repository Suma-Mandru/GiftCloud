package com.giftydelivery.service;

import com.giftydelivery.model.Order;
import com.giftydelivery.repository.OrderRepository;
import com.giftydelivery.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GiftService giftService; // ✅ Required for stock management

    // ✅ Place Order - with stock deduction
    public Order placeOrder(Order order) {
        if (order.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        // ✅ Deduct stock for the gift
        giftService.reduceStock(order.getGiftId(), order.getQuantity());

        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());

        return orderRepository.save(order);
    }

    // ✅ Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // ✅ Get pending orders scheduled for delivery
    public List<Order> getPendingOrdersToDeliver() {
        return orderRepository.findByStatusAndScheduledTimeBefore("PENDING", LocalDateTime.now());
    }

    // ✅ Mark order as delivered
    public void markAsDelivered(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus("DELIVERED");
        orderRepository.save(order);
    }
    public void updateOrder(Long id, Order updatedOrder) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setQuantity(updatedOrder.getQuantity());
        order.setScheduledTime(updatedOrder.getScheduledTime());
        order.setStatus(updatedOrder.getStatus());

        orderRepository.save(order);
    }
    public String getOrderStatus(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));
        return order.getStatus();
    }


    // ✅ Delete an order
//    public void deleteOrder(Long id) {
//        orderRepository.deleteById(id);
//    }
}
