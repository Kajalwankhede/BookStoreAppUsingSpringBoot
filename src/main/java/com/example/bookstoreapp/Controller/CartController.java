package com.example.bookstoreapp.Controller;
import com.example.bookstoreapp.DTO.CartDTO;
import com.example.bookstoreapp.DTO.ResponseCartDTO;
import com.example.bookstoreapp.Model.Cart;
import com.example.bookstoreapp.Service.ICartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/cart")
public class CartController {
@Autowired
    ICartService cartService;
//Adding cart details in the database
@PostMapping("/addTocart")
    public ResponseEntity<ResponseCartDTO> insertToCart(@Valid @RequestBody CartDTO cartDTO) {
        Cart cart = cartService.addBookToCart(cartDTO);
        ResponseCartDTO responseCartDTO = new ResponseCartDTO("Your cart details are added!", cart);
        return new ResponseEntity<>(responseCartDTO, HttpStatus.CREATED);
}
// Getting particular cart details by id
@GetMapping("/getById/{id}")
    public ResponseEntity<ResponseCartDTO> getById(@PathVariable Long id) {
          Cart cart = cartService.getById(id);
        ResponseCartDTO responseCartDTO = new ResponseCartDTO(" Cart details by id is found!", cart);
        return new ResponseEntity<>(responseCartDTO, HttpStatus.FOUND);
}
//Getting all cart details present in the database
 @GetMapping("/getAllData")
    public ResponseEntity<ResponseCartDTO> getAll() {
        List<Cart> cartList = cartService.getAll();
        ResponseCartDTO responseCartDTO = new ResponseCartDTO("All cart details are found!", cartList);
        return new ResponseEntity<>(responseCartDTO, HttpStatus.FOUND);
}
//Deleting particular cart details which will be found by id
 @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseCartDTO> removeBookFromCart(@PathVariable Long id) {
        cartService.removeById(id);
        ResponseCartDTO responseCartDTO = new ResponseCartDTO("Cart details is deleted!", "Deleted cart id is: " + id);
        return new ResponseEntity<>(responseCartDTO, HttpStatus.GONE);
}
// Updating particular cart details which will be found by id
@PutMapping("/updateById/{id}")
    public ResponseEntity<ResponseCartDTO> updateById(@Valid @RequestBody CartDTO cartDTO,@PathVariable Long id){
        Cart cart=cartService.updateById(cartDTO,id);
        ResponseCartDTO responseCartDTO=new ResponseCartDTO("Your cart details is updated!",cart);
        return new ResponseEntity<>(responseCartDTO,HttpStatus.ACCEPTED);
}
//Updating quantity for particular cart by id
@PutMapping("/updateQuantity/{id}")
    public ResponseEntity<ResponseCartDTO> updateQuantity(@Valid @RequestBody CartDTO cartDTO,@PathVariable Long id){
        Cart cart=cartService.UpdateQuantity(cartDTO,id);
        ResponseCartDTO responseCartDTO=new ResponseCartDTO("Your cart quantity is updated!",cart);
        return new ResponseEntity<>(responseCartDTO,HttpStatus.ACCEPTED);
    }

}
