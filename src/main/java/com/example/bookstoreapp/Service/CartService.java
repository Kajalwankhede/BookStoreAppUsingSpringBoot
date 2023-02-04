package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.DTO.CartDTO;
import com.example.bookstoreapp.Exception.CartException;
import com.example.bookstoreapp.Model.Book;
import com.example.bookstoreapp.Model.Cart;
import com.example.bookstoreapp.Model.User;
import com.example.bookstoreapp.Repository.BookRepository;
import com.example.bookstoreapp.Repository.CartRepository;
import com.example.bookstoreapp.Repository.UserRepository;
import com.example.bookstoreapp.Util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class CartService implements ICartService {
@Autowired
    UserRepository userRepository;
 @Autowired
    TokenUtil tokenUtil;
 @Autowired
    BookRepository bookRepository;
@Autowired
    CartRepository cartRepository;
//Adding book to the cart
 @Override
    public Cart addBookToCart(CartDTO cartDTO) {
         Optional<User> user = userRepository.findById(cartDTO.getUserId());
        Optional<Book> book = bookRepository.findById(cartDTO.getBookId());
        if (user.isPresent() && book.isPresent()) {
            if (book.get().getQuantity() >= cartDTO.getQuantity() && cartDTO.getQuantity() > 0) {
                double totalPrice = cartDTO.getQuantity() * book.get().getPrice();
                Cart cartDetails = new Cart(user.get(), book.get(), cartDTO.getQuantity(),
                        totalPrice);
                return cartRepository.save(cartDetails);
            } else {
                throw (new CartException("not found"));
            }
        } else {
            throw (new CartException("Record not Found"));
        }
    }
    //List of book to the cart
 @Override
    public List<Cart> getAll() {
        List<Cart> cartListData = cartRepository.findAll();
        return cartListData;
}
//Getting book to the cart by id
@Override
    public Cart getById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new CartException("Invalid book id:  " + id));
}
@Override
    public void removeById(Long id) {   //Deleted cart details by cart id
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isPresent()) {
            cartRepository.deleteById(id);

        } else {
            throw (new CartException("Record not Found"));
        }
}
// Updating cart by id
@Override
public Cart updateById(CartDTO cartDTO,Long id){
    Optional<User> user=userRepository.findById(cartDTO.getUserId());
    Optional<Book> book=bookRepository.findById(cartDTO.getBookId());
    Cart cart=cartRepository.findById(id).get();
    if(cartRepository.findById(id).isPresent() && book.isPresent() && user.isPresent()){
        cart.setUser(user.get());
        cart.setBook(book.get());
        cart.setQuantity(cartDTO.getQuantity());
        cartRepository.save(cart);
        return cart;
    }else {
        throw new CartException("Invalid");
    }
}
// Updating quantity for particular cart which will be found by id
@Override
    public Cart UpdateQuantity(CartDTO cartDTO, Long id){
        Cart cart=cartRepository.findById(id).get();
        if(cartRepository.findById(id).isPresent()){
            cart.setQuantity(cartDTO.getQuantity());
            cartRepository.save(cart);
            return cart;
        }else {
            throw new CartException("Invalid id: "+id);
        }
    }

}