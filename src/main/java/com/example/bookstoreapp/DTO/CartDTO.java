package com.example.bookstoreapp.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartDTO {
 private Long userId;
 private Long bookId;

 public int quantity;
}