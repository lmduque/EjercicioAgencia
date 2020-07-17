package co.com.udem.ejercicioagencia.rest.contoller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.udem.ejercicioagencia.dto.UsuarioDTO;
import co.com.udem.ejercicioagencia.entities.Usuario;
import co.com.udem.ejercicioagencia.respositories.UsuarioRepository;
import co.com.udem.ejercicioagencia.util.Constantes;
import co.com.udem.ejercicioagencia.util.ConvertUsuario;

@RestController
public class UsuarioRestController {
	private static final Logger logger = LogManager.getLogger(UsuarioRestController.class);
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ConvertUsuario convertUsuario;

	@PostMapping("/usuarios/adicionarUsuario")
	public Map<String, String> adicionarClubFutbol(@RequestBody UsuarioDTO usuarioDTO) {
		Map<String, String> response = new HashMap<>();
		try {
			Usuario usuario = convertUsuario.convertToEntity(usuarioDTO);
			usuarioRepository.save(usuario);
			response.put(Constantes.CODIGO_HTTP, "200");
			response.put(Constantes.MENSAJE_EXITO, "Usuario insertado exitosamente");
			return response;
		} catch (ParseException e) {
			response.put(Constantes.CODIGO_HTTP, "500");
			response.put(Constantes.MENSAJE_ERROR, "Ocurrió un problema al insertar el usuario");
			return response;
		}
	}

	@GetMapping("/usuarios/{id}")
	public UsuarioDTO buscarUsuario(@PathVariable Long id) {
		UsuarioDTO usuarioDTO = null;
		Usuario usuario;
		try {
			usuario = usuarioRepository.findById(id).get();
			usuarioDTO = convertUsuario.convertToDTO(usuario);
		} catch (ParseException e) {
			BasicConfigurator.configure();
			logger.info(e);

		}
		return usuarioDTO;

	}

	@DeleteMapping("/usuarios/{id}")
	public Map<String, String> eliminarUsuario(@PathVariable Long id) {
		Map<String, String> response = new HashMap<>();

		try {
			usuarioRepository.deleteById(id);
			response.put(Constantes.CODIGO_HTTP, "200");
			response.put(Constantes.MENSAJE_EXITO, "Usuario eliminado exitosamente");
			return response;
		} catch (Exception e) {
			response.put(Constantes.CODIGO_HTTP, "500");
			response.put(Constantes.MENSAJE_ERROR, "Ocurrió un problema al eliminar el usuario");
			return response;
		}
	}

	@GetMapping("/usuarios")
	public Iterable<UsuarioDTO> listarUsuarios() {
		List<UsuarioDTO> listaUsuarios = new ArrayList<UsuarioDTO>();
		UsuarioDTO usuarioDTO;
		Iterator<Usuario> it = usuarioRepository.findAll().iterator();
		while (it.hasNext()) {
			Usuario u = (Usuario) it.next();

			try {
				usuarioDTO = convertUsuario.convertToDTO(u);
				listaUsuarios.add(usuarioDTO);
			} catch (ParseException e) {
				BasicConfigurator.configure();
				logger.info(e);
			}
		}

		return listaUsuarios;
	}
	
	@PutMapping("/usuarios/{id}")
	public Map<String, String> updateUser(@RequestBody UsuarioDTO newUser, @PathVariable Long id) {
		Map<String, String> response = new HashMap<>();

		try {			
			Usuario user = usuarioRepository.findById(id).get();

			if (newUser.getNombres() != null) {
				user.setNombres(newUser.getNombres());
			}

			if (newUser.getApellidos() != null) {
				user.setApellidos(newUser.getApellidos());
			}

			if (newUser.getTipoIdentificacion() != null) {
				user.setTipoIdentificacion(newUser.getTipoIdentificacion());
			}

			if (newUser.getNumeroIdentificacion() != null) {
				user.setNumeroIdentificacion(newUser.getNumeroIdentificacion());
			}

			if (newUser.getDireccion() != null) {
				user.setDireccion(newUser.getDireccion());
			}

			if (newUser.getTelefono() != null) {
				user.setTelefono(newUser.getTelefono());
			}

			if (newUser.getEmail() != null) {
				user.setEmail(newUser.getEmail());
			}

			if (newUser.getPassword() != null) {
				user.setPassword(newUser.getPassword());
			}

			usuarioRepository.save(user);

			response.put(Constantes.CODIGO_HTTP, "200");
			response.put(Constantes.MENSAJE_EXITO, "Usuario actualizado exitosamente");
			return response;
		} catch (Exception e) {
			response.put(Constantes.CODIGO_HTTP, "500");
			response.put(Constantes.MENSAJE_ERROR, "Ocurrió un problema al actualizar el usuario");
			return response;
		}

	}

}
