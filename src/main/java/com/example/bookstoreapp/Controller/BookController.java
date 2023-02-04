package com.example.bookstoreapp.Controller;
import com.example.bookstoreapp.DTO.BookDTO;
import com.example.bookstoreapp.DTO.ResponseBookDTO;
import com.example.bookstoreapp.Model.Book;
import com.example.bookstoreapp.Service.IBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/book")
public class BookController {
@Autowired
    IBookService bookService;
    // Adding book details to the database.
 @PostMapping("/add")
    public ResponseEntity<ResponseBookDTO> insertBook(@Valid @RequestBody BookDTO bookDTO){
        Book book=bookService.insertBookData(bookDTO);
        ResponseBookDTO responseDTO=new ResponseBookDTO("Book details Added!",book);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
 //Getting all book details
@GetMapping("/getAll")
    public ResponseEntity<ResponseBookDTO> getAllBook(){
        List<Book> bookList=bookService.getAllBook();
        ResponseBookDTO responseDTO=new ResponseBookDTO("List of books: ",bookList);
        return new ResponseEntity<>(responseDTO,HttpStatus.FOUND);
}
// Getting book details by id
@GetMapping("/getById/{id}")
    public ResponseEntity<ResponseBookDTO> geBooktById(@PathVariable Long id){
        Optional<Book> book=bookService.getById(id);
        ResponseBookDTO responseBookDTO=new ResponseBookDTO(" Book by id found!",book);
        return new ResponseEntity<>(responseBookDTO,HttpStatus.FOUND);
}
// Deleting book details by the book id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseBookDTO> deleteById(@PathVariable Long id){
        bookService.deleteById(id);
        ResponseBookDTO responseBookDTO=new ResponseBookDTO("Book details deleted!","Deleted book id is: "+id);
        return new ResponseEntity<>(responseBookDTO,HttpStatus.GONE);
 }
// book details update by id
@PutMapping("/updateById/{id}")
    public ResponseEntity<ResponseBookDTO> updateBookById(@Valid @RequestBody BookDTO bookDTO,@PathVariable Long id){
        Book book=bookService.updateBookById(bookDTO,id);
        ResponseBookDTO responseBookDTO=new ResponseBookDTO(" Book details updated!",book);
        return new ResponseEntity<>(responseBookDTO,HttpStatus.ACCEPTED);
 }
// Updating book quantity by id
@PutMapping("/updateQuantity/{id}")
    public ResponseEntity<ResponseBookDTO> changeBookQuantity(@Valid @RequestBody BookDTO bookDTO,@PathVariable Long id){
        Book book=bookService.updateQuantity(bookDTO,id);
        ResponseBookDTO responseBookDTO=new ResponseBookDTO(" book quantity is updated!",book);
        return new ResponseEntity<>(responseBookDTO,HttpStatus.ACCEPTED);
}
@PutMapping("/changePrice/{id}")
    public ResponseEntity<ResponseBookDTO> ChangeBookPrice(@Valid @RequestBody BookDTO bookDTO,@PathVariable Long id){
        Book book=bookService.updatePrice(bookDTO,id);
        ResponseBookDTO responseBookDTO=new ResponseBookDTO(" book price is updated!",book);
        return new ResponseEntity<>(responseBookDTO,HttpStatus.ACCEPTED);
    }
}
