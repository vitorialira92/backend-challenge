package mercadoeletronico.BackendChallenge.dtos.order;


import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Order details for order creation")
public class OrderCreationDTO {
    @Schema(description = "Order identifier")
    public Long pedido;
    @Schema(description = "Order items")

    public List<OrderItemDTO> itens;
}