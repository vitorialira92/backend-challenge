package mercadoeletronico.BackendChallenge.dtos.order;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(description = "Order details for order creation")
public class OrderCreationDTO {
    @Schema(description = "Order identifier")
    @NotNull
    public String pedido;
    @Schema(description = "Order items")
    @NotNull
    public List<OrderItemDTO> itens;
}