package com.giftydelivery.controller;

import com.giftydelivery.model.Order;
import com.giftydelivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // ✅ Place order: stock is reduced internally
    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }

    // ✅ View all orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // ✅ Mark order as delivered (can add admin check if needed)
    @PutMapping("/deliver/{id}")
    public ResponseEntity<String> markOrderAsDelivered(@PathVariable("id") Long id) {
        orderService.markAsDelivered(id);
        return ResponseEntity.ok("Order marked as delivered");
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<String> checkOrderStatus(@PathVariable("id") Long id) {
        String status = orderService.getOrderStatus(id);
        return ResponseEntity.ok("Order Status: " + status);
    }

    
    // ✅ Delete order
//    @DeleteMapping("/{id}")
//    public String deleteOrder(@PathVariable Long id) {
//        orderService.deleteOrder(id);
//        return "Order deleted successfully";
//    }
}
