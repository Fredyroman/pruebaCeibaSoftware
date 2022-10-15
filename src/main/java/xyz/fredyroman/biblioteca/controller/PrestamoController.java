package xyz.fredyroman.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.fredyroman.biblioteca.Dto.PrestamoDto;
import xyz.fredyroman.biblioteca.exception.BusinessException;
import xyz.fredyroman.biblioteca.exception.RequestException;
import xyz.fredyroman.biblioteca.exception.RequestMessage;
import xyz.fredyroman.biblioteca.model.Prestamo;
import xyz.fredyroman.biblioteca.service.PrestamoService;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/prestamo")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @GetMapping
    public ResponseEntity<Page<Prestamo>> getAllPrestamo (
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "0") Integer size,
            @RequestParam(required = false, defaultValue = "false")Boolean enablePagination){
        return ResponseEntity.ok(prestamoService.getAllPrestamo(page,size,enablePagination));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Prestamo>> findPrestamoById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(prestamoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PrestamoDto> savePrestamo(@Valid @RequestBody Prestamo prestamo){
        ResponseEntity<Prestamo> prestamoAlmacenado = ResponseEntity.status(HttpStatus.OK).body(prestamoService.savePrestamo(prestamo));
        throw new RequestMessage( Long.toString(prestamoAlmacenado.getBody().getId()),HttpStatus.OK , retornaFechaString(prestamoAlmacenado.getBody().getFechaMaximadevolucion()));
    }

    @PutMapping
    private ResponseEntity<Prestamo> updatePrestamo(@Valid @RequestBody Prestamo prestamo){
        return ResponseEntity.status(HttpStatus.OK).body(prestamoService.updatePrestamo(prestamo));
    }

    @DeleteMapping(value = "/{id}")
    public void deletePrestamo(@PathVariable("id") Long id){
        prestamoService.deletePrestamo(id);
        ResponseEntity.ok(!prestamoService.existById(id));
    }

    public String retornaFechaString(Date fechaMaximaDevolucion){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fechaMaximaDevolucion);
    }





}
