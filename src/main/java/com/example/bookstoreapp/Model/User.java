package com.example.bookstoreapp.Model;
import com.example.bookstoreapp.DTO.UserDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import java.time.LocalDate;
@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {
@GeneratedValue(strategy = GenerationType.IDENTITY)
 @Id
    private Long userId;
@NonNull
    private String firstName;
@NonNull
    private String lastName;
@NonNull
    private String address;
@NonNull
    private LocalDate dob;
@NonNull
    private String email;
@NonNull
    private String password;

public User(UserDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.address = userDTO.getAddress();
        this.dob = userDTO.getDob();
        this.password = userDTO.getPassword();
    }
}