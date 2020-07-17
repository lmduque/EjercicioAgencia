package co.com.udem.ejercicioagencia.respositories;

import org.springframework.data.repository.CrudRepository;

import co.com.udem.ejercicioagencia.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
