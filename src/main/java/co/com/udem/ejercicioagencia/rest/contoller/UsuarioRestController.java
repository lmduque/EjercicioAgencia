package co.com.udem.ejercicioagencia.rest.contoller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.udem.ejercicioagencia.dto.TipoIdentificacionDTO;
import co.com.udem.ejercicioagencia.dto.UsuarioDTO;
import co.com.udem.ejercicioagencia.entities.TipoIdentificacion;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	@PostMapping("/usuario/ingresarUsuario")
    public Map<String, String> ingresarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Map<String, String> response = new HashMap<>();
        try {
			
			
			usuarioRepository.save(Usuario.builder()
					.numeroIdentificacion(usuarioDTO.getNumeroIdentificacion())
					.nombres(usuarioDTO.getNombres())
					.apellidos(usuarioDTO.getApellidos())
					.direccion(usuarioDTO.getDireccion())
					.telefono(usuarioDTO.getTelefono())
					.email(usuarioDTO.getEmail())
					.password(passwordEncoder.encode(usuarioDTO.getPassword()))
					.tipoIdentificacion(new TipoIdentificacion(usuarioDTO.getTipoIdentificacionDTO().getId(),usuarioDTO.getTipoIdentificacionDTO().getCodigo(),usuarioDTO.getTipoIdentificacionDTO().getDescripcion()))
					.roles(Arrays.asList( "ROLE_USER"))
					.build()
					);				
			

			response.put(Constantes.CODIGO_HTTP, "200");
			response.put(Constantes.MENSAJE_EXITO, "Usuario insertado exitosamente");
			return response;
		} catch (Exception e) {
			response.put(Constantes.CODIGO_HTTP, "500");
			response.put(Constantes.MENSAJE_ERROR, "Ocurrió un problema al insertar el usuario");
			return response;
		}
       
    }
		
	
	@GetMapping("/usuario/{numeroIdentificacion}")
	public UsuarioDTO buscarUsuario(@PathVariable Long numeroIdentificacion) {
		
		Optional<Usuario> usuario;
		
		usuario = usuarioRepository.findById(numeroIdentificacion);
		if (usuario.isPresent()) {			
			return new UsuarioDTO(usuario.get().getNumeroIdentificacion(),usuario.get().getNombres(), usuario.get().getApellidos(),
					              usuario.get().getDireccion(), usuario.get().getTelefono(), usuario.get().getEmail(),usuario.get().getPassword(),new TipoIdentificacionDTO(null,usuario.get().getTipoIdentificacion().getCodigo(),usuario.get().getTipoIdentificacion().getDescripcion()));
		} else {
			return null;
		}	
		
	}

	@DeleteMapping("/usuario/{numeroIdentificacion}")
	public Map<String, String> eliminarUsuario(@PathVariable Long numeroIdentificacion) {
		Map<String, String> response = new HashMap<>();

		try {
			usuarioRepository.deleteById(numeroIdentificacion);
			response.put(Constantes.CODIGO_HTTP, "200");
			response.put(Constantes.MENSAJE_EXITO, "Usuario eliminado exitosamente");
			return response;
		} catch (Exception e) {
			response.put(Constantes.CODIGO_HTTP, "500");
			response.put(Constantes.MENSAJE_ERROR, "Ocurrió un problema al eliminar el usuario");
			return response;
		}
	}
	
	@GetMapping("/usuario")
	public Iterable<UsuarioDTO> listfindAll() {

		List<UsuarioDTO> listUsuarioDto = new ArrayList<>();		
		for (Usuario usuario : usuarioRepository.findAll()) {
			listUsuarioDto.add(new UsuarioDTO(usuario.getNumeroIdentificacion(), usuario.getNombres(), usuario.getApellidos(),
					                          usuario.getDireccion(), usuario.getTelefono(), usuario.getEmail(), usuario.getPassword(), 
					           new TipoIdentificacionDTO(usuario.getTipoIdentificacion().getId(),usuario.getTipoIdentificacion().getCodigo(),
							                            usuario.getTipoIdentificacion().getDescripcion())

			));

		}
		
		return listUsuarioDto;

	}

	@PutMapping("/usuario/{numeroIdentificacion}")
	public Map<String, String> actualizarUsuario(@RequestBody UsuarioDTO newUser, @PathVariable Long numeroIdentificacion) {
	
		Map<String, String> response = new HashMap<>();

		try {			
			Usuario user = usuarioRepository.findById(numeroIdentificacion).get();

			if (newUser.getNombres() != null) {
				user.setNombres(newUser.getNombres());
			}

			if (newUser.getApellidos() != null) {
				user.setApellidos(newUser.getApellidos());
			}

			if (newUser.getTipoIdentificacionDTO().getCodigo() != null) {				
				user.setTipoIdentificacion(new TipoIdentificacion(newUser.getTipoIdentificacionDTO().getId(),newUser.getTipoIdentificacionDTO().getCodigo(),newUser.getTipoIdentificacionDTO().getDescripcion()));
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
				user.setPassword(passwordEncoder.encode(newUser.getPassword()));				
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
