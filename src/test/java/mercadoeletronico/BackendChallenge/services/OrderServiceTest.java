package mercadoeletronico.BackendChallenge.services;

import mercadoeletronico.BackendChallenge.domain.Item;
import mercadoeletronico.BackendChallenge.domain.Order;
import mercadoeletronico.BackendChallenge.domain.OrderItem;
import mercadoeletronico.BackendChallenge.dtos.order.OrderCreationDTO;
import mercadoeletronico.BackendChallenge.dtos.status_change.StatusChange;
import mercadoeletronico.BackendChallenge.dtos.status_change.StatusChangeRequestDTO;
import mercadoeletronico.BackendChallenge.dtos.status_change.StatusChangeResponseDTO;
import mercadoeletronico.BackendChallenge.exception.DuplicateCreationAttemptException;
import mercadoeletronico.BackendChallenge.exception.ResourceNotFoundException;
import mercadoeletronico.BackendChallenge.repositories.ItemRepository;
import mercadoeletronico.BackendChallenge.repositories.OrderItemRepository;
import mercadoeletronico.BackendChallenge.repositories.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderItemRepository orderItemRepository;
    @Mock
    private ItemRepository itemRepository;
    @InjectMocks
    private OrderService orderService;

    @Test
    public void findOrderByIdShouldReturnTheExceptedOrder() throws ResourceNotFoundException {
        Order expectedOrder = new Order();
        expectedOrder.setId("1");

        when(orderRepository.findById("1")).thenReturn(Optional.of(expectedOrder));

        Order actualOrder = orderService.getOrderById("1");

        assertNotNull(expectedOrder);
        assertEquals(expectedOrder.getId(), actualOrder.getId());
    }

    @Test
    public void findOrderByIdShouldThrowAnException() throws ResourceNotFoundException {
        when(orderRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows( ResourceNotFoundException.class, () -> {
                    orderService.getOrderById("1");
                });
    }

    @Test
    public void createOrderShouldThrowAnException() throws DuplicateCreationAttemptException {
        when(orderRepository.findById("123456")).thenReturn(Optional.of(getStandardOrder()));

        OrderCreationDTO orderCreationDTO = new OrderCreationDTO();
        orderCreationDTO.pedido = "123456";

        assertThrows(DuplicateCreationAttemptException.class, () -> {
                    orderService.createOrder(orderCreationDTO);
                });
    }

    @ParameterizedTest
    @MethodSource("statusChangeExpectedResponse")
    public void changeStatusReturnsTheRightStatus(StatusChangeTestData requestAndExpectedResponse) {
        if(requestAndExpectedResponse.request.pedido.equals("123456")){
            Order order = getStandardOrder();

            when(orderRepository.findById("123456")).thenReturn(Optional.of(order));
        }
        else
            when(orderRepository.findById("123456-N")).thenReturn(Optional.empty());

        StatusChangeResponseDTO actualResponse = orderService.setOrderStatus(requestAndExpectedResponse.request);

        assertEquals(actualResponse.status.size(), requestAndExpectedResponse.response.status.size());
        for(String status : requestAndExpectedResponse.response.status){
            assertTrue(actualResponse.status.contains(status));
        }
    }

    static Stream<StatusChangeTestData> statusChangeExpectedResponse() {
        return Stream.of(
                new StatusChangeTestData(new StatusChangeRequestDTO(//1
                        StatusChange.APROVADO, 3, 20, "123456"
                    ), new StatusChangeResponseDTO(
                        "123456",  new ArrayList<>(Arrays.asList("APROVADO"))
                    )),
                new StatusChangeTestData(new StatusChangeRequestDTO(//2
                        StatusChange.APROVADO, 3, 10, "123456"
                    ), new StatusChangeResponseDTO(
                        "123456",  new ArrayList<>(Arrays.asList("APROVADO_VALOR_A_MENOR"))
                    )),
                new StatusChangeTestData(new StatusChangeRequestDTO(//3
                        StatusChange.APROVADO, 4, 21, "123456"
                    ), new StatusChangeResponseDTO(
                        "123456",  new ArrayList<>(Arrays.asList("APROVADO_VALOR_A_MAIOR", "APROVADO_QTD_A_MAIOR"))
                    )),
                new StatusChangeTestData(new StatusChangeRequestDTO(//4
                        StatusChange.APROVADO, 2, 20, "123456"
                    ), new StatusChangeResponseDTO(
                        "123456",  new ArrayList<>(Arrays.asList("APROVADO_QTD_A_MENOR"))
                    )),
                new StatusChangeTestData(new StatusChangeRequestDTO(//5
                        StatusChange.REPROVADO, 0, 0, "123456"
                    ), new StatusChangeResponseDTO(
                        "123456",  new ArrayList<>(Arrays.asList("REPROVADO"))
                    )),
                new StatusChangeTestData(new StatusChangeRequestDTO(//6
                        StatusChange.APROVADO, 3, 20, "123456-N"
                    ), new StatusChangeResponseDTO(
                        "123456-N",  new ArrayList<>(Arrays.asList("CODIGO_PEDIDO_INVALIDO"))
                    ))
        );
    }
    
    private static Order getStandardOrder(){
        Order order = new Order();

        Item itemA = new Item();
        itemA.setDescription("Item A");
        itemA.setUnityPrice(10);
        OrderItem orderItemA = new OrderItem();
        orderItemA.setItem(itemA);
        orderItemA.setUnityPrice(10);
        orderItemA.setQuantity(1);

        Item itemB = new Item();
        itemB.setDescription("Item B");
        itemB.setUnityPrice(5);
        OrderItem orderItemB = new OrderItem();
        orderItemB.setItem(itemB);
        orderItemB.setUnityPrice(5);
        orderItemB.setQuantity(2);

        List<OrderItem> items = new ArrayList<>();
        items.add(orderItemA);
        items.add(orderItemB);

        order.setItems(items);
        order.setId("123456");
        
        return order;
    }
}
