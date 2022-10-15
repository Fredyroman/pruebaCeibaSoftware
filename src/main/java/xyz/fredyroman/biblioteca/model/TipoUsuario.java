package xyz.fredyroman.biblioteca.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tipo_usuario")
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private boolean estado;
}
