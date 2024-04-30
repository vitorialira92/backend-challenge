package mercadoeletronico.BackendChallenge.exception;

public class DuplicateCreationAttemptException extends Exception{
    public Long id;
    public String resourceName;
    public DuplicateCreationAttemptException(Long id, String resourceName){
        this.id = id;
        this.resourceName = resourceName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
