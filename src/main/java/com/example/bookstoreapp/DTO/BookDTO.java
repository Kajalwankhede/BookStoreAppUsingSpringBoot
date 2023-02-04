package com.example.bookstoreapp.DTO;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class BookDTO {
private String bookName;
private String authorName;
private String bookDescription;
private String bookLogo;
private long price;
private long quantity;
}