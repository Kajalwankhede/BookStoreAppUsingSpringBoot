package com.example.bookstoreapp.Model;
import com.example.bookstoreapp.DTO.BookDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
@Id
 @Column
 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @NonNull
    private String bookName;
    @NonNull
    private String authorName;
    @NonNull
    private String bookDescription;
    @NonNull
    private String bookLogo;
    @NonNull
    private long price;
    @NonNull
    private long quantity;
 public Book(BookDTO bookDTO){
        this.bookName=bookDTO.getBookName();
        this.authorName=bookDTO.getAuthorName();
        this.bookDescription=bookDTO.getBookDescription();
        this.bookLogo=bookDTO.getBookLogo();
        this.price=bookDTO.getPrice();
        this.quantity=bookDTO.getQuantity();
    }
}

