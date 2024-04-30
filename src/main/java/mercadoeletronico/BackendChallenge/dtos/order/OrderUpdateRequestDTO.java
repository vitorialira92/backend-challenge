package mercadoeletronico.BackendChallenge.dtos.order;


import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Order detaisl for update")

public class OrderUpdateRequestDTO {
    @Schema(description = "Items updated")

    public List<OrderItemDTO> itens;
}
