package mercadoeletronico.BackendChallenge.dtos.status_change;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Input to change an order's status")
public class StatusChangeRequestDTO {
    public StatusChange status;
    public int itensAprovados;
    public double valorAprovado;
    public Long pedido;
}
