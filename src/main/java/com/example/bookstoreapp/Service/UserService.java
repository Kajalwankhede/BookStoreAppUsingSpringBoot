package com.example.bookstoreapp.Service;
import com.example.bookstoreapp.DTO.LoginDTO;
import com.example.bookstoreapp.DTO.UserDTO;
import com.example.bookstoreapp.Exception.UserException;
import com.example.bookstoreapp.Model.User;
import com.example.bookstoreapp.Repository.UserRepository;
import com.example.bookstoreapp.Util.EmailSenderService;
import com.example.bookstoreapp.Util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class UserService implements IUserService {
@Autowired
    UserRepository userRepository;
@Autowired
    TokenUtil tokenUtil;
 @Autowired
    EmailSenderService emailSenderService;

@Override
    public User register(UserDTO userDTO)  {
        User user=new User(userDTO);
        String token=tokenUtil.createToken(user.getUserId());
        userRepository.save(user);
        emailSenderService.sendEmail(user.getEmail(),"\n\n Welcome To BookStore App !!!!!!!! \n\n User Registered Successfully", "Hello "+user.getFirstName() +user.getLastName()+"\n\n Your Details  are:\n\n User Id:  "+user.getUserId()+"\n First Name:  "+user.getFirstName()+"\n Last Name:  "+user.getLastName()+"\n Email:  "+user.getEmail()+"\n Address:  "+user.getAddress()+"\n DOB:  "+user.getDob()+"\n Token:  "+token);
        return user;
    }
 @Override
    public String login(LoginDTO loginDTO) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(loginDTO.getEmail()));
        if (user.isPresent() && user.get().getPassword().equals(loginDTO.getPassword())) {
            emailSenderService.sendEmail(user.get().getEmail(), "You have Log in successfully!", "Hello " + user.get().getFirstName()+ user.get().getLastName());
            return "Login Done !!!!!";
        } else {
            throw new UserException(" Email or Password incorrect!");
        }
}
 @Override
    public List<User> getAllDetails() {

        return userRepository.findAll();
 }
@Override
    public Optional<User> getUserDataById(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user;
        } else {
            throw new UserException("Incorrect id:  " + id);
        }
}
@Override
    public User forgotPassword(UserDTO userDTO, String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setPassword(userDTO.getPassword());
            userRepository.save(user);
            emailSenderService.sendEmail(user.getEmail(), "Password Changed Successfully !!!!!", "Hello " + user.getFirstName() + "  Password Changed successfully !\n\n  New password: " + user.getPassword());
            return user;
        } else {
            throw new UserException("Not found User Email: " + email);
        }
 }
// Updating user details by email id
 @Override
    public User updateUserDataById(UserDTO userDTO, Long id) {
        User user = userRepository.getByUserId(id);
        if (userRepository.getByUserId(id) != null) {
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setAddress(userDTO.getAddress());
            user.setDob(userDTO.getDob());
            user.setPassword(userDTO.getPassword());
            userRepository.save(user);
            emailSenderService.sendEmail(user.getEmail(), "Details updated Successfully.!", "Hello " + user.getFirstName() + " Your updated details are:\n\n First Name:  " + user.getFirstName() + "\n Last Name:  " + user.getLastName() + "\n Address:  " + user.getAddress() + "\n DOB:  " + user.getDob()  +
                    "\n Email:  " + user.getEmail()   + "\n Password:  " + user.getPassword());
            return user;
        } else {
            throw new UserException("Incorrect id: " + id);
        }
  }
@Override
    public void deleteById(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            emailSenderService.sendEmail(user.get().getEmail(), "User Details Deleted successfully!", "Hello " + user.get().getFirstName() + " User id: " + id);
        } else {
            throw new UserException("Incorrect Id:  " + id);
        }
 }

}