package mercadoeletronico.BackendChallenge.exception;

public class DuplicateCreationAttemptException extends Exception{
    public String id;
    public String resourceName;
    public DuplicateCreationAttemptException(String id, String resourceName){
        this.id = id;
        this.resourceName = resourceName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
