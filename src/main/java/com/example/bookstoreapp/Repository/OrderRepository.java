package com.example.bookstoreapp.Repository;
import com.example.bookstoreapp.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
Optional<Order> findById(Long orderId);
Order getById(Long id);
}