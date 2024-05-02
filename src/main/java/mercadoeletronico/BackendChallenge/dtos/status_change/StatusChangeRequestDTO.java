package mercadoeletronico.BackendChallenge.dtos.status_change;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Input to change an order's status")
public class StatusChangeRequestDTO {
    public StatusChange status;
    public int itensAprovados;
    public double valorAprovado;
    public String pedido;

    public StatusChangeRequestDTO(){}
    public StatusChangeRequestDTO(StatusChange status, int itensAprovados, double valorAprovado, String pedido) {
        this.status = status;
        this.itensAprovados = itensAprovados;
        this.valorAprovado = valorAprovado;
        this.pedido = pedido;
    }
}
