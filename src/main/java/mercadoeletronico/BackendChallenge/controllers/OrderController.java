package mercadoeletronico.BackendChallenge.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import mercadoeletronico.BackendChallenge.domain.Order;
import mercadoeletronico.BackendChallenge.dtos.order.OrderCreationDTO;
import mercadoeletronico.BackendChallenge.dtos.order.OrderUpdateRequestDTO;
import mercadoeletronico.BackendChallenge.dtos.status_change.StatusChangeRequestDTO;
import mercadoeletronico.BackendChallenge.dtos.status_change.StatusChangeResponseDTO;
import mercadoeletronico.BackendChallenge.exception.DuplicateCreationAttemptException;
import mercadoeletronico.BackendChallenge.exception.ResourceNotFoundException;
import mercadoeletronico.BackendChallenge.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Management of orders")
@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/pedido/{id}")
    public ResponseEntity<Order> getOrder(@Parameter(description = "Order Id", example = "48486589")
                               @PathVariable String id) throws ResourceNotFoundException {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @GetMapping("/pedido/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @PostMapping("/pedido")
    public ResponseEntity<Order> createOrder(@RequestBody OrderCreationDTO orderDTO) throws DuplicateCreationAttemptException {
        Order order = orderService.createOrder(orderDTO);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/pedido/{id}")
    public ResponseEntity<Order> updateOrder(@Parameter(description = "Order Id", example = "48486589") @PathVariable String id,
                                             @RequestBody OrderUpdateRequestDTO orderDTO) throws ResourceNotFoundException {
        Order order = orderService.updateOrder(id, orderDTO);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/pedido/{id}")
    public void deleteOrder(@PathVariable String id) throws ResourceNotFoundException {
        orderService.deleteOrder(id);
    }

    @PostMapping("/status")
    public ResponseEntity<StatusChangeResponseDTO> setOrderStatus(@RequestBody StatusChangeRequestDTO statusRequest) {
        StatusChangeResponseDTO response = orderService.setOrderStatus(statusRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
