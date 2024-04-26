package mercadoeletronico.BackendChallenge.services;


import mercadoeletronico.BackendChallenge.domain.Order;
import mercadoeletronico.BackendChallenge.domain.OrderItem;
import mercadoeletronico.BackendChallenge.dtos.order.OrderCreationDTO;
import mercadoeletronico.BackendChallenge.dtos.order.OrderUpdateRequestDTO;
import mercadoeletronico.BackendChallenge.exception.ResourceNotFoundException;
import mercadoeletronico.BackendChallenge.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    public Order getOrderById(Long id){
        return repository.getReferenceById(id);
    }

    public Order createOrder(OrderCreationDTO orderDto){
        List<OrderItem> orderItems = new ArrayList<>();
        Order order = new Order();
        order.setItems(orderItems);

        return repository.save(order);
    }

    public Order updateOrder(Long id, OrderUpdateRequestDTO orderDto) throws ResourceNotFoundException {
        Order order = repository.getReferenceById(id);
        if(order == null)
            throw new ResourceNotFoundException("Order");
        order.setItems(orderDto.itens);

        return repository.save(order);
    }

    public void deleteOrder(Long id){
        Order order = repository.getReferenceById(id);

        repository.delete(order);
    }
}
