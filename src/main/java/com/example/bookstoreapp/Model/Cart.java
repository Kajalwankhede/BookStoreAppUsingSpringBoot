package com.example.bookstoreapp.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long cartId;
    @OneToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public User user;
   @ManyToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Book book;
    public int quantity;
    public double totalPrice;
 public Cart(User user, Book book, int quantity, double totalPrice) {
        this.user = user;
        this.book = book;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

}