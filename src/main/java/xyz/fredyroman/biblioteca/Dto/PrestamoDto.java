package xyz.fredyroman.biblioteca.Dto;

import lombok.Builder;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Builder
public class PrestamoDto {

    private String id;
    private String fechaMaximaDevolucion;


}
