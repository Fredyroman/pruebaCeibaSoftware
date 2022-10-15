package xyz.fredyroman.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity
@Table(name = "prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 10)
    private String isbn;

    @Column(name = "identificacion_usuario", length = 10)
    private String identificacionUsuario;
    @ManyToOne
    @JoinColumn(name = "id_tipo_usuario")
    private TipoUsuario tipoUsuario;

    @JsonFormat(pattern = "dd/MM/yyyyy")
    @Column(name = "fecha_maxima_devolucion")
    private Date fechaMaximadevolucion;

}
