package com.example.bookstoreapp.Controller;
import com.example.bookstoreapp.DTO.LoginDTO;
import com.example.bookstoreapp.DTO.ResponseUserDTO;
import com.example.bookstoreapp.DTO.UserDTO;
import com.example.bookstoreapp.Model.User;
import com.example.bookstoreapp.Service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/user")
public class UserController {
 @Autowired
    IUserService userService;
 //Adding user details for register
@PostMapping("/register")
    public ResponseEntity<ResponseUserDTO> registerUser(@Valid @RequestBody UserDTO userDTO)  {
        User user = userService.register(userDTO);
        ResponseUserDTO responseUserDTO = new ResponseUserDTO("User Registered Successfully!", user);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.CREATED);
}
//Login to the user
 @PostMapping("/login")
    public ResponseEntity<ResponseUserDTO> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        String loginUser = userService.login(loginDTO);
        ResponseUserDTO responseUserDTO = new ResponseUserDTO(" User Login successfully", loginUser);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.OK);
}
// Getting all user details
 @GetMapping("/getAllData")
    public ResponseEntity<ResponseUserDTO> getAllData() {
        List<User> user = userService.getAllDetails();
        ResponseUserDTO responseUserDTO = new ResponseUserDTO("List of user Details: ", user);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.FOUND);
 }
//Getting  user details  by id
@GetMapping("/getById/{id}")
    public ResponseEntity<ResponseUserDTO> getByUserId(@PathVariable Long id) {
        Optional<User> user = userService.getUserDataById(id);
        ResponseUserDTO responseUserDTO = new ResponseUserDTO("Getting data for id: ", user);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.FOUND);
}
// Changing password using id
 @PutMapping("/forgotPassword/{email}")
    public ResponseEntity<ResponseUserDTO> forgotPassword(@Valid @RequestBody UserDTO userDTO, @PathVariable String email) {
        User user = userService.forgotPassword(userDTO, email);
        ResponseUserDTO responseUserDTO = new ResponseUserDTO("Password changed successfully!", user);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.ACCEPTED);
 }
// Updating user details by  id
@PutMapping("/updateById/{id}")
    public ResponseEntity<ResponseUserDTO> updateUserById(@Valid @RequestBody UserDTO userDTO, @PathVariable Long id) {
        User userData = userService.updateUserDataById(userDTO, id);
        ResponseUserDTO responseUserDTO = new ResponseUserDTO(" User Details updated successfully!", userData);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.OK);
 }
@DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseUserDTO> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        ResponseUserDTO responseUserDTO = new ResponseUserDTO("User details  deleted successfully", "Deleted user id is: " + id);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.GONE);
    }

}