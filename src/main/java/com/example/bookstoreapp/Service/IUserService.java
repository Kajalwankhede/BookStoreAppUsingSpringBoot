package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.DTO.LoginDTO;
import com.example.bookstoreapp.DTO.UserDTO;
import com.example.bookstoreapp.Model.User;
import java.util.List;
import java.util.Optional;
public interface IUserService {
 User register(UserDTO userDTO);
List<User> getAllDetails();
 Optional<User> getUserDataById(long id);
 User updateUserDataById(UserDTO userDTO, Long id);
User forgotPassword(UserDTO userDTO, String email);
String login(LoginDTO loginDTO);
 void deleteById(long id);
}