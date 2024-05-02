package mercadoeletronico.BackendChallenge.services;

import mercadoeletronico.BackendChallenge.dtos.status_change.StatusChangeRequestDTO;
import mercadoeletronico.BackendChallenge.dtos.status_change.StatusChangeResponseDTO;

public class StatusChangeTestData {
    public StatusChangeRequestDTO request;
    public StatusChangeResponseDTO response;

    public StatusChangeTestData() {
    }

    public StatusChangeTestData(StatusChangeRequestDTO request, StatusChangeResponseDTO response) {
        this.request = request;
        this.response = response;
    }
}
