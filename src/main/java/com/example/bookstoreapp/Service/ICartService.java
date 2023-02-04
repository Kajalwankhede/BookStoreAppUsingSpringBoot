package com.example.bookstoreapp.Service;
import com.example.bookstoreapp.DTO.CartDTO;
import com.example.bookstoreapp.Model.Cart;
import java.util.List;
public interface ICartService {
 public Cart addBookToCart(CartDTO cartDTO);
public List<Cart> getAll();
 public Cart getById(Long id);
void removeById(Long id);
 public Cart UpdateQuantity(CartDTO cartDTO, Long id);
 public Cart updateById(CartDTO cartDTO,Long id);

}