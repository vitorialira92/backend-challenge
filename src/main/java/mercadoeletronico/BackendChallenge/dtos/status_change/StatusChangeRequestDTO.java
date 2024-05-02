package mercadoeletronico.BackendChallenge.dtos.status_change;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Input to change an order's status")
public class StatusChangeRequestDTO {
    @NotNull
    public StatusChange status;
    @NotNull
    @Min(value = 0,  message = "Approved items must be a positive number")
    public int itensAprovados;
    @NotNull
    @DecimalMin(value = "0.0", message = "Approved amount must be a positive number")
    public double valorAprovado;
    @NotNull
    public String pedido;

    public StatusChangeRequestDTO(){}
    public StatusChangeRequestDTO(StatusChange status, int itensAprovados, double valorAprovado, String pedido) {
        this.status = status;
        this.itensAprovados = itensAprovados;
        this.valorAprovado = valorAprovado;
        this.pedido = pedido;
    }
}
