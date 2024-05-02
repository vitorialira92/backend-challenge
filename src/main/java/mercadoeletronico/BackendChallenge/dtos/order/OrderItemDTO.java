package mercadoeletronico.BackendChallenge.dtos.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Order item details")
public class OrderItemDTO {
    @Schema(description = "Item description")
    @NotNull
    public String descricao;
    @Schema(description = "Item unity price")
    @NotNull
    @DecimalMin(value = "0.01", message = "Unity price must be more than 0")
    public double precoUnitario;
    @Schema(description = "Item quantity")
    @Min(value = 1, message = "Quantity must be at least 1")
    public int qtd;
}
