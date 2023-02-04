package com.example.bookstoreapp.DTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
@Data
@NoArgsConstructor
public class UserDTO {
 @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}$",message = "Firstname start with capital letter")
    private String firstName;
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}$",message = "Lastname start with capital letter")
    private String lastName;
   private String address;
  private LocalDate dob;
    @Email(message = "Enter valid Email !")
    private String email;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[@#$%^&*()-+=])(?=.*[0-9])([a-zA-Z0-9@._-]).{8,}$",message = "Password need at least 1 special character, 1 Caps letter,small letter, 1 digit and have 8 characters")
    private String password;
}