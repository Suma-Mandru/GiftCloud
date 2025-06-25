package com.giftydelivery.scheduler;

import com.giftydelivery.model.Order;
import com.giftydelivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeliveryScheduler {

    @Autowired
    private OrderService orderService;

    @Scheduled(fixedRate = 60000) // Runs every 60 seconds
    public void checkAndDeliverOrders() {
        List<Order> dueOrders = orderService.getPendingOrdersToDeliver();
        for (Order order : dueOrders) {
            System.out.println("✅ Delivering order: " + order.getId());
            orderService.markAsDelivered(order.getId());
        }
    }
}
