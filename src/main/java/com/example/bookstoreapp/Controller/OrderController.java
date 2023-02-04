package com.example.bookstoreapp.Controller;
import com.example.bookstoreapp.DTO.OrderDTO;
import com.example.bookstoreapp.DTO.ResponseOrderDTO;
import com.example.bookstoreapp.Model.Order;
import com.example.bookstoreapp.Service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/order")
public class OrderController {
@Autowired
    IOrderService iOrderService;
//Placing order by the user id
@PostMapping("/placeOrder/{userId}")
    public ResponseEntity<ResponseOrderDTO> placeOrder(@PathVariable Long userId, @RequestBody OrderDTO orderDto) {
        Order order = iOrderService.placeOrder(userId,orderDto);
        ResponseOrderDTO response= new ResponseOrderDTO("Order Placed",  order.getId());
        return new ResponseEntity<> (response, HttpStatus.OK);
}
//Cancelling Order by the order id and user id.
 @PutMapping("/cancelOrder/{orderId}/{userId}")
    public ResponseEntity<ResponseOrderDTO> cancelOrder(@PathVariable Long orderId,@PathVariable Long userId) {
        String order = iOrderService.cancelOrder(orderId,userId);
        ResponseOrderDTO response= new ResponseOrderDTO("Order Cancelled ", order);
        return new ResponseEntity<>(response, HttpStatus.OK);
 }
// Getting all order details present in the database
 @GetMapping("/getAll")
    public ResponseEntity<ResponseOrderDTO> getAll(){
        List<Order> orderList=iOrderService.getAll();
        ResponseOrderDTO responseOrderDTO=new ResponseOrderDTO("All order details are found!",orderList);
        return new ResponseEntity<>(responseOrderDTO,HttpStatus.FOUND);
 }
 // particular order details which will be found by id
 @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseOrderDTO> getById(@PathVariable Long id){
     ResponseOrderDTO responseOrderDTO=new ResponseOrderDTO(" order details by id is found!", String.valueOf(iOrderService.getById(id)));
        return new ResponseEntity<>(responseOrderDTO,HttpStatus.FOUND);
    }
//Deleting particular order details which will be found by order id
    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<ResponseOrderDTO> deleteById(@PathVariable Long orderId){
        String result=iOrderService.deleteById(orderId);
        ResponseOrderDTO responseOrderDTO=new ResponseOrderDTO("Order details is deleted!",result);
        return new ResponseEntity<>(responseOrderDTO,HttpStatus.GONE);
    }

}