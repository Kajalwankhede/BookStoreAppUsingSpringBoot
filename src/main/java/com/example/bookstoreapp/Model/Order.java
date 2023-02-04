package com.example.bookstoreapp.Model;
import com.example.bookstoreapp.DTO.OrderDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orderData")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate orderDate;
    private int quantity;
    private String address;
    @JoinColumn(name = "user_id")
    @OneToOne()

    private User user;
    @JoinColumn(name = "book_id")
    @ManyToOne()

    private Book book;
    private boolean cancel = false;
    private Long orderPrice;
public Order(User user, Book book,Long orderPrice, OrderDTO orderDto) {
        this.user = user;
        this.book = book;
        this.orderDate = LocalDate.now();
        this.quantity = orderDto.getQuantity();
        this.address = orderDto.getAddress();
        this.orderPrice = orderPrice;
    }
}