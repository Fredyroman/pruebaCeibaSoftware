package xyz.fredyroman.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import xyz.fredyroman.biblioteca.model.Prestamo;

import java.util.List;

@Repository
public interface IPrestamoRepository extends JpaRepository<Prestamo, Long> {

    @Query(value = "SELECT * FROM public.prestamo where identificacion_usuario = ?1 and id_tipo_usuario = ?2", nativeQuery = true)
    List<Prestamo> consultarRegistrosUsuarioInvitado( String identificacionUsuario,  Long filtro2);


}
