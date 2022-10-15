package xyz.fredyroman.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import xyz.fredyroman.biblioteca.exception.BusinessException;
import xyz.fredyroman.biblioteca.exception.RequestException;
import xyz.fredyroman.biblioteca.model.Prestamo;
import xyz.fredyroman.biblioteca.repository.IPrestamoRepository;
import xyz.fredyroman.biblioteca.repository.ITipoUsuarioRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {

    @Autowired
    private IPrestamoRepository iPrestamoRepository;

    @Autowired
    private ITipoUsuarioRepository iTipoUsuarioRepository;



    public Prestamo savePrestamo(Prestamo prestamo){

        if(prestamo.getId() == null){
            if(validarCamposObligatorios(prestamo)){
                if(existeTipoUsuario(prestamo.getTipoUsuario().getId())){
                    calcularFechasDevolucionLibro(prestamo);
                    prestamo =  iPrestamoRepository.save(prestamo);
                }else{
                    throw new BusinessException("P-400", HttpStatus.BAD_REQUEST ,"Mensaje : Tipo de usuario no permitido en la biblioteca");
                }
            }
        }
       return prestamo;

    }

    public boolean validarCamposObligatorios(Prestamo prestamo){

        boolean bandera = true;

        if (prestamo.getIsbn().equals("") || prestamo.getIsbn() == null){
            bandera = false;
            throw new RequestException("P-401","Isbn del libro es requerido");
        } else if (prestamo.getIdentificacionUsuario().equals("") || prestamo.getIdentificacionUsuario() == null){
            bandera = false;
            throw new RequestException("P-401","El identificador del Usuario es requerido");
        } else if(existeRegistrosUsuarioInvitado(prestamo.getIdentificacionUsuario(), prestamo.getTipoUsuario().getId())){
            bandera = false;
            throw new BusinessException("P-400",HttpStatus.BAD_REQUEST ,"Mensaje : El usuario con identificacion " + prestamo.getIdentificacionUsuario() +" ya tiene un libro prestado por cual no se puede realizar otro prestamo");
        }

        return bandera;
    }

    public Page<Prestamo> getAllPrestamo(Integer page, Integer size, Boolean enablePagination){
        return iPrestamoRepository.findAll(enablePagination ? PageRequest.of(page, size): Pageable.unpaged());
    }

    public List<Prestamo> findAll(){
        return iPrestamoRepository.findAll();
    }

    public Optional<Prestamo> findById(Long id){
        return iPrestamoRepository.findById(id);
    }

    public void deletePrestamo(long id){
        iPrestamoRepository.deleteById(id);
    }

    public Prestamo updatePrestamo(Prestamo prestamo){
        if(prestamo.getId() != null && iPrestamoRepository.existsById(prestamo.getId())){
            iPrestamoRepository.save(prestamo);
        }
        return null;
    }

    public boolean existeRegistrosUsuarioInvitado(String numeroDocumento, Long idTipoUsuario){
        List <Prestamo> listaPrestamo = iPrestamoRepository.consultarRegistrosUsuarioInvitado(numeroDocumento, idTipoUsuario);
        System.out.println("listaPrestamo.size(): " + listaPrestamo.size());
        if(listaPrestamo.size() > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean existById(Long id){
        return iPrestamoRepository.existsById(id);
    }

    public boolean existeTipoUsuario(Long id){
        return iTipoUsuarioRepository.existsById(id);
    }
    public void calcularFechasDevolucionLibro(Prestamo prestamo){

        if(prestamo.getTipoUsuario().getId() == 1){
            prestamo.setFechaMaximadevolucion(sumarDiasFechaDevolucion(10));
        } else if (prestamo.getTipoUsuario().getId() == 2) {
            prestamo.setFechaMaximadevolucion(sumarDiasFechaDevolucion(8));
        } else if (prestamo.getTipoUsuario().getId() == 3) {
            prestamo.setFechaMaximadevolucion(sumarDiasFechaDevolucion(7));
        }

    }

    public Date sumarDiasFechaDevolucion(int dias){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date()); 
        calendar.add(Calendar.DAY_OF_YEAR, dias);

        return calendar.getTime(); 

    }


}
