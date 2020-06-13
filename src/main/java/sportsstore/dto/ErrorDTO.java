package sportsstore.dto;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorDTO extends Exception {
    private Response.Status status;
    private Object error;

    public ErrorDTO(Response.Status status, Object error) {
        this.status = status;
        this.error = error;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public Response.Status getStatus() {
        return status;
    }

    public void setStatus(Response.Status status) {
        this.status = status;
    }
}