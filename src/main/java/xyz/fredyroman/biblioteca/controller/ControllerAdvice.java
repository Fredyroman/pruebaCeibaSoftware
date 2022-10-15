package xyz.fredyroman.biblioteca.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.fredyroman.biblioteca.Dto.PrestamoDto;
import xyz.fredyroman.biblioteca.exception.BusinessException;
import xyz.fredyroman.biblioteca.exception.RequestException;
import xyz.fredyroman.biblioteca.Dto.ErrorDto;
import xyz.fredyroman.biblioteca.exception.RequestMessage;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDto> runtimeExceptionHandler(RuntimeException ex){
        ErrorDto error = ErrorDto.builder().code("P-500").message(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<ErrorDto> RequestExceptionHandler(RequestException ex){
        ErrorDto error = ErrorDto.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ErrorDto> businessExceptionHandler(BusinessException ex){
        ErrorDto error = ErrorDto.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(value = RequestMessage.class)
    public ResponseEntity<PrestamoDto> businessRequestMessage(RequestMessage ex){
        PrestamoDto mensaje = PrestamoDto.builder().id(ex.getId()).fechaMaximaDevolucion(ex.getMessage()).build();
        return new ResponseEntity<>(mensaje, ex.getStatus());
    }

}
