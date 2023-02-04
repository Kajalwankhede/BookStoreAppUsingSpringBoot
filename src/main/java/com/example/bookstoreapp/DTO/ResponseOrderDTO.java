package com.example.bookstoreapp.DTO;
import com.example.bookstoreapp.Model.Order;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@NoArgsConstructor
public class ResponseOrderDTO {
    private String message;
    private Object object;
public ResponseOrderDTO(String message, List<Order> order) {
        this.message=message;
        this.object=order;
 }
public ResponseOrderDTO(String message, Long orderList) {
        this.message=message;
        this.object=orderList;
}
 public ResponseOrderDTO(String exception_while_processing_rest_request, String message) {
        this.message=exception_while_processing_rest_request;
        this.message=message;
    }
}
