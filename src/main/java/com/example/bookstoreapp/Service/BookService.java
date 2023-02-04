package com.example.bookstoreapp.Service;
import com.example.bookstoreapp.DTO.BookDTO;
import com.example.bookstoreapp.Exception.BookException;
import com.example.bookstoreapp.Model.Book;
import com.example.bookstoreapp.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class BookService implements IBookService {
@Autowired
    BookRepository bookRepository;
 @Override
    public Book insertBookData(BookDTO bookDTO) {
        Book book = new Book(bookDTO);
        bookRepository.save(book);
        return book;
 }
 @Override
    public List<Book> getAllBook() {
        List<Book> bookList = bookRepository.findAll();
        return bookList;
}
//book details  found by id
 @Override
    public Optional<Book> getById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book;
        } else {
            throw new BookException(" Id not found: " + id);
        }
 }
@Override
    public void deleteById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new BookException(" Id not found: " + id);
        }
}
    // book details which will be found by id
 @Override
  public Book updateBookById(BookDTO bookDTO, Long id) {
        Book book = bookRepository.findById(id).get();
        if (bookRepository.findById(id).isPresent()) {
            book.setBookName(bookDTO.getBookName());
            book.setAuthorName(bookDTO.getAuthorName());
            book.setBookDescription(book.getBookDescription());
            book.setBookLogo(bookDTO.getBookLogo());
            book.setPrice(bookDTO.getPrice());
            book.setQuantity(bookDTO.getQuantity());
            bookRepository.save(book);
            return book;
        } else {
            throw new BookException("Id not found: " + id);
        }
}
 //book updated  by id
@Override
    public Book updateQuantity(BookDTO bookDTO, Long id) {
        Optional<Book> bookList = bookRepository.findById(id);
        if (bookList.isPresent()) {
            Book book = bookRepository.findById(id).get();
            book.setQuantity(bookDTO.getQuantity());
            bookRepository.save(book);
            return book;
        } else {
            throw new BookException("Id not found: " + id);
        }
}
  @Override
    public Book updatePrice(BookDTO bookDTO, Long id) {
        Optional<Book> bookList = bookRepository.findById(id);
        if (bookList.isPresent()) {
            Book book = bookRepository.findById(id).get();
            book.setPrice(bookDTO.getPrice());
            bookRepository.save(book);
            return book;
        } else {
            throw new BookException("Id not found: " + id);
        }
    }
}