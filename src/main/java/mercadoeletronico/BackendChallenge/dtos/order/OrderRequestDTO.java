package mercadoeletronico.BackendChallenge.dtos.order;

import mercadoeletronico.BackendChallenge.domain.OrderItem;

import java.util.List;

public class OrderRequestDTO {
    public Long pedido;
    public List<OrderItem> itens;
}