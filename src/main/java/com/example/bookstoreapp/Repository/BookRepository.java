package com.example.bookstoreapp.Repository;
import com.example.bookstoreapp.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    @Query(value = "select * from bookstore.book where book.book_name= :name",nativeQuery = true)
    Book findByName(String name);

}