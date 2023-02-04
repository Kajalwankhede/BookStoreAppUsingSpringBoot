package com.example.bookstoreapp.Service;
import com.example.bookstoreapp.DTO.BookDTO;
import com.example.bookstoreapp.Model.Book;
import java.util.List;
import java.util.Optional;
public interface IBookService {
Book insertBookData(BookDTO bookDTO);
List<Book> getAllBook();
Optional<Book> getById(Long id);
 void deleteById(Long id);
Book updateBookById(BookDTO bookDTO, Long id);
Book updateQuantity(BookDTO bookDTO, Long id);
 Book updatePrice(BookDTO bookDTO, Long id);

}
