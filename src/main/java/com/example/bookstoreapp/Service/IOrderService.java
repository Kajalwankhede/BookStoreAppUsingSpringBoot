package com.example.bookstoreapp.Service;
import com.example.bookstoreapp.DTO.OrderDTO;
import com.example.bookstoreapp.Model.Order;
import java.util.List;
public interface IOrderService {
Order placeOrder(Long userId, OrderDTO orderDto);
String cancelOrder(Long orderId, Long userId);
 List<Order> getAll();
 Order getById(Long id);

 public String deleteById(Long orderId);
}
