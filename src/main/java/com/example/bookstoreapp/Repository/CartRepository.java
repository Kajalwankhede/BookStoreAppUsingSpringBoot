package com.example.bookstoreapp.Repository;
import com.example.bookstoreapp.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
    @Query(value = "select * from bookstore.cart where user_id=:userId",nativeQuery = true)
    List<Cart> findByUserId(Long userId);
   @Query(value = "select cart_id from bookstore.cart where cart.user_id=:user",nativeQuery = true)
    Long findByCartUser(Long user);

}