package mercadoeletronico.BackendChallenge.dtos.order;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Order item details")
public class OrderItemDTO {
    @Schema(description = "Item description")
    public String descricao;
    @Schema(description = "Item unity price")

    public double precoUnitario;
    @Schema(description = "Item quantity")
    public int qtd;
}
