package mercadoeletronico.BackendChallenge.services;


import mercadoeletronico.BackendChallenge.domain.Item;
import mercadoeletronico.BackendChallenge.domain.Order;
import mercadoeletronico.BackendChallenge.domain.OrderItem;
import mercadoeletronico.BackendChallenge.dtos.order.OrderCreationDTO;
import mercadoeletronico.BackendChallenge.dtos.order.OrderItemDTO;
import mercadoeletronico.BackendChallenge.dtos.order.OrderUpdateRequestDTO;
import mercadoeletronico.BackendChallenge.exception.ResourceNotFoundException;
import mercadoeletronico.BackendChallenge.repositories.ItemRepository;
import mercadoeletronico.BackendChallenge.repositories.OrderItemRepository;
import mercadoeletronico.BackendChallenge.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ItemRepository itemRepository;

    public Order getOrderById(Long id){
        return orderRepository.getReferenceById(id);
    }

    public Order createOrder(OrderCreationDTO orderDto){
        List<OrderItem> orderItems = new ArrayList<>();

        for(OrderItemDTO orderItemDTO : orderDto.itens){
            Item item = createItem(orderItemDTO);
            item = itemRepository.save(item);

            OrderItem orderItem = createOrderItem(orderItemDTO, item);
            orderItem = orderItemRepository.save(orderItem);

            orderItems.add(orderItem);
        }

        Order order = new Order();
        order.setId(orderDto.pedido);
        order.setItems(orderItems);

        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, OrderUpdateRequestDTO orderDto) throws ResourceNotFoundException {
        Order order = orderRepository.getReferenceById(id);
        if(order == null)
            throw new ResourceNotFoundException("Order");
        List<OrderItem> orderItems = new ArrayList<>();

        for(OrderItemDTO orderItemDTO : orderDto.itens){
            Item item = createItem(orderItemDTO);
            item = itemRepository.save(item);

            OrderItem orderItem = createOrderItem(orderItemDTO, item);

            orderItem = orderItemRepository.save(orderItem);


            orderItems.add(orderItem);
        }

        order.setItems(orderItems);

        return orderRepository.save(order);
    }

    public void deleteOrder(Long id){
        Order order = orderRepository.getReferenceById(id);

        orderRepository.delete(order);
    }

    private Item createItem(OrderItemDTO orderItemDTO){
        Item item = new Item();

        item.setDescription(orderItemDTO.descricao);
        item.setUnityPrice(orderItemDTO.precoUnitario);

        return item;
    }

    private OrderItem createOrderItem(OrderItemDTO orderItemDTO, Item item){
        OrderItem orderItem = new OrderItem();

        orderItem.setItem(item);
        orderItem.setQuantity(orderItemDTO.qtd);
        orderItem.setUnityPrice(orderItemDTO.precoUnitario);

        return orderItem;
    }
}
