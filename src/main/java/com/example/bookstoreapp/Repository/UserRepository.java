package com.example.bookstoreapp.Repository;
import com.example.bookstoreapp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "select * from bookstore.user where user.email= :email",nativeQuery = true)
    User findByEmail(String email);
     User getByUserId(Long id);
}