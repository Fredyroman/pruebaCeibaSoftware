package xyz.fredyroman.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.fredyroman.biblioteca.model.TipoUsuario;

public interface ITipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {
}
