package com.example.bookstoreapp.Service;
import com.example.bookstoreapp.DTO.OrderDTO;
import com.example.bookstoreapp.Exception.OrderException;
import com.example.bookstoreapp.Model.Book;
import com.example.bookstoreapp.Model.Order;
import com.example.bookstoreapp.Model.User;
import com.example.bookstoreapp.Repository.BookRepository;
import com.example.bookstoreapp.Repository.CartRepository;
import com.example.bookstoreapp.Repository.OrderRepository;
import com.example.bookstoreapp.Repository.UserRepository;
import com.example.bookstoreapp.Util.EmailSenderService;
import com.example.bookstoreapp.Util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class OrderService implements IOrderService {
@Autowired
    private BookRepository bookRepository;
 @Autowired
    private UserRepository userRepository;
@Autowired
    private OrderRepository orderRepository;
@Autowired
    private CartRepository cartRepository;
@Autowired
    private EmailSenderService mailService;
 @Autowired
    TokenUtil tokenUtil;
//Placing order for the particular user by id
 @Override
    public Order placeOrder(Long userId, OrderDTO orderDto) {
        User user = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findById(orderDto.getBookId()).orElse(null);
        if (user != null) {
            Long orderPrice = book.getPrice() * orderDto.getQuantity();
            book.setQuantity(book.getQuantity() - orderDto.getQuantity());

            Order order = new Order(user, book, orderPrice, orderDto);
            orderRepository.save(order);
            cartRepository.deleteAll();
            mailService.sendEmail(user.getEmail(), "Order Placed", "Book Name :" + order.getBook().getBookName() + "\n" + " Description :" + order.getBook().getBookDescription() + "\n" + " Price :" + order.getBook().getPrice() +
                    "\n" + " Quantity :" + orderDto.getQuantity() + "\n" + "Order Price :" + orderPrice);
            return order;
        }
        return null;
}
//Cancelling order by order id and user id
 @Override
    public String cancelOrder(Long orderId, Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Order order = orderRepository.findById(orderId).orElse(null);
            if (order != null) {
                order.setCancel(true);
                Book book = bookRepository.findById(order.getBook().getBookId()).orElse(null);
                book.setQuantity(book.getQuantity() + order.getQuantity());
                mailService.sendEmail(user.getEmail(), "Order Cancelled", "Order Id " + orderId + "\n" + order);
                orderRepository.save(order);
                return "order cancelled";

            }
        }

        return "user not found";
 }
//All order details present in the database
    @Override
    public List<Order> getAll() {
        List<Order> orderList = orderRepository.findAll();
        return orderList;
}
//Getting particular order details  by id
@Override
    public Order getById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new OrderException("Sorry! We can not find order id: " + id);
        }
 }
 //Deleting particular order details which will be found by id
@Override
    public String deleteById(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        if (orderRepository.findById(orderId).isPresent()) {
            orderRepository.deleteById(orderId);
            return "Details has been deleted!";
        } else {
            throw new OrderException("Sorry! We can not find order id: " + orderId);
        }
    }
}
