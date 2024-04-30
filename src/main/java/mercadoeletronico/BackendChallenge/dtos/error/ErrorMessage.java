package mercadoeletronico.BackendChallenge.dtos.error;

public class ErrorMessage {
    public int statusCode;
    public String message;

    public ErrorMessage(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
