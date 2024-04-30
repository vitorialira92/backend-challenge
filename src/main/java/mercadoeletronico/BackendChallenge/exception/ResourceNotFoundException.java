package mercadoeletronico.BackendChallenge.exception;

public class ResourceNotFoundException extends Exception{
    public String resourceName;

    public ResourceNotFoundException(String resourceName){
        this.resourceName = resourceName;
    }
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
