package xyz.fredyroman.biblioteca.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RequestMessage extends RuntimeException{
    private String id;
    private HttpStatus status;

    public RequestMessage(String id, HttpStatus status, String fechaMaximaDevolucion){
        super(fechaMaximaDevolucion);
        this.id = id;
        this.status = status;
    }
}
