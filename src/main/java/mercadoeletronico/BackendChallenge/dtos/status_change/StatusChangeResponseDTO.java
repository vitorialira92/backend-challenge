package mercadoeletronico.BackendChallenge.dtos.status_change;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Response to the change of status of an order")

public class StatusChangeResponseDTO {
    public List<String> status;
}
