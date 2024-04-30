package mercadoeletronico.BackendChallenge.services;


import mercadoeletronico.BackendChallenge.domain.Item;
import mercadoeletronico.BackendChallenge.domain.Order;
import mercadoeletronico.BackendChallenge.domain.OrderItem;
import mercadoeletronico.BackendChallenge.dtos.order.OrderCreationDTO;
import mercadoeletronico.BackendChallenge.dtos.order.OrderItemDTO;
import mercadoeletronico.BackendChallenge.dtos.order.OrderUpdateRequestDTO;
import mercadoeletronico.BackendChallenge.dtos.status_change.OrderStatus;
import mercadoeletronico.BackendChallenge.dtos.status_change.StatusChange;
import mercadoeletronico.BackendChallenge.dtos.status_change.StatusChangeRequestDTO;
import mercadoeletronico.BackendChallenge.dtos.status_change.StatusChangeResponseDTO;
import mercadoeletronico.BackendChallenge.exception.DuplicateCreationAttemptException;
import mercadoeletronico.BackendChallenge.exception.ResourceNotFoundException;
import mercadoeletronico.BackendChallenge.repositories.ItemRepository;
import mercadoeletronico.BackendChallenge.repositories.OrderItemRepository;
import mercadoeletronico.BackendChallenge.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ItemRepository itemRepository;

    public Order getOrderById(Long id) throws ResourceNotFoundException {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order"));
    }

    public Order createOrder(OrderCreationDTO orderDto) throws DuplicateCreationAttemptException {
        Optional<Order> existingOrder = orderRepository.findById(orderDto.pedido);

        if(existingOrder.isPresent())
            throw new DuplicateCreationAttemptException(orderDto.pedido, "order");

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
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order"));

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

    public void deleteOrder(Long id) throws ResourceNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order"));

        orderRepository.delete(order);
    }

    public StatusChangeResponseDTO setOrderStatus(StatusChangeRequestDTO statusRequest) {
        StatusChangeResponseDTO response = new StatusChangeResponseDTO();
        Optional<Order> order = orderRepository.findById(statusRequest.pedido);
        if(order.isEmpty()){
            response.status = List.of(OrderStatus.CODIGO_PEDIDO_INVALIDO.name());
            return response;
        }
        List<String> statusList = new ArrayList<>();

        if(statusRequest.status == StatusChange.REPROVADO){
            statusList.add(OrderStatus.REPROVADO.name());
        }else{
            if(statusRequest.valorAprovado < order.get().getTotalPrice())
                statusList.add(OrderStatus.APROVADO_VALOR_A_MENOR.name());

            if(statusRequest.valorAprovado > order.get().getTotalPrice())
                statusList.add(OrderStatus.APROVADO_VALOR_A_MAIOR.name());

            if(statusRequest.itensAprovados < order.get().getTotalItemsQuantity())
                statusList.add(OrderStatus.APROVADO_QTD_A_MENOR.name());

            if(statusRequest.itensAprovados > order.get().getTotalItemsQuantity())
                statusList.add(OrderStatus.APROVADO_QTD_A_MAIOR.name());

            if(statusList.isEmpty())
                statusList.add(OrderStatus.APROVADO.name());
        }
        response.status = statusList;
        return response;
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


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
