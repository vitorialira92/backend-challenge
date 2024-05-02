package mercadoeletronico.BackendChallenge.services;

import mercadoeletronico.BackendChallenge.domain.Order;
import mercadoeletronico.BackendChallenge.exception.ResourceNotFoundException;
import mercadoeletronico.BackendChallenge.repositories.ItemRepository;
import mercadoeletronico.BackendChallenge.repositories.OrderItemRepository;
import mercadoeletronico.BackendChallenge.repositories.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
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
    public void testFindOrderById() throws ResourceNotFoundException {
        Order expectedOrder = new Order();
        expectedOrder.setId("1");

        when(orderRepository.findById("1")).thenReturn(Optional.of(expectedOrder));

        Order actualOrder = orderService.getOrderById(1L);

        assertNotNull(expectedOrder);
        assertEquals(expectedOrder.getId(), actualOrder.getId());
    }

}
