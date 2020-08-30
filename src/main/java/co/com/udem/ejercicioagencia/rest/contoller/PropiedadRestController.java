package co.com.udem.ejercicioagencia.rest.contoller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.udem.ejercicioagencia.dto.PropiedadDTO;
import co.com.udem.ejercicioagencia.dto.TipoIdentificacionDTO;
import co.com.udem.ejercicioagencia.dto.UsuarioDTO;
import co.com.udem.ejercicioagencia.entities.Propiedad;
import co.com.udem.ejercicioagencia.entities.TipoIdentificacion;
import co.com.udem.ejercicioagencia.entities.Usuario;
import co.com.udem.ejercicioagencia.respositories.PropiedadRepository;
import co.com.udem.ejercicioagencia.util.Constantes;
import co.com.udem.ejercicioagencia.util.ConvertPropiedad;

@RestController
public class PropiedadRestController {
	@Autowired
	PropiedadRepository propiedadRepository; 
	
	@Autowired
	ConvertPropiedad ConvertPropiedad;
	
	@PostMapping("/propiedad/ingresar")
	public Map<String, String> ingresarPropiedad(@RequestBody PropiedadDTO propiedadDTO) {
		Map<String, String> response = new HashMap<>();
		try {
			Propiedad propiedad = ConvertPropiedad.convertToEntity(propiedadDTO);
			propiedadRepository.save(propiedad);
			response.put(Constantes.CODIGO_HTTP, "200");
			response.put(Constantes.MENSAJE_EXITO, "Propiedad ingresada exitosamente");
			return response;
		} catch (ParseException e) {
			response.put(Constantes.CODIGO_HTTP, "500");
			response.put(Constantes.MENSAJE_ERROR, "Ocurrió un problema al ingresar la propiedad");
			return response;
		}
	}
	
	
	@GetMapping("/propiedad/consultarporcaracteristicas/{identificacion}")
	public List<PropiedadDTO> buscarPropiedad(@PathVariable Long identificacion,
			                                  @RequestParam(value = "precio", required = false, defaultValue = "") Double precio,
			                                  @RequestParam(value = "numeroHabitaciones", required = false, defaultValue = "") Integer numeroHabitaciones,
			                                  @RequestParam(value = "area", required = false, defaultValue = "") String area) {
		
		
		List<PropiedadDTO> listPropiedadDto = new ArrayList<>();	
		for (Propiedad propiedad : propiedadRepository.consultarPropiedadesUsuario(identificacion, precio,
				numeroHabitaciones, area)) {
			
			listPropiedadDto.add(new PropiedadDTO(propiedad.getId(),
				      propiedad.getArea(),
		              propiedad.getNumeroHabitaciones(),
					  propiedad.getNumeroBanios(),
					  propiedad.getTipocontrato(),
					  propiedad.getValor(),									  
				      new UsuarioDTO(propiedad.getUsuario().getNumeroIdentificacion(),
								     propiedad.getUsuario().getNombres(),
									 propiedad.getUsuario().getApellidos(),
									 propiedad.getUsuario().getDireccion(),
									 propiedad.getUsuario().getTelefono(),
									 propiedad.getUsuario().getEmail(),
									 propiedad.getUsuario().getPassword(),
									 new TipoIdentificacionDTO(propiedad.getUsuario().getTipoIdentificacion().getId(),
											 				   propiedad.getUsuario().getTipoIdentificacion().getCodigo(),
															   propiedad.getUsuario().getTipoIdentificacion().getDescripcion()
															   )
									)
					)
			);

		}
		return listPropiedadDto;
	}

	
	@GetMapping("/propiedad/consultar/{identificacion}")
	public List<PropiedadDTO> buscarPropiedad(@PathVariable Long identificacion) {
		
		
		List<PropiedadDTO> listPropiedadDto = new ArrayList<>();	
		for (Propiedad propiedad : propiedadRepository.consultarPropiedadesUsuario(identificacion)) {
			
			listPropiedadDto.add(new PropiedadDTO(propiedad.getId(),
				      propiedad.getArea(),
		              propiedad.getNumeroHabitaciones(),
					  propiedad.getNumeroBanios(),
					  propiedad.getTipocontrato(),
					  propiedad.getValor(),									  
				      new UsuarioDTO(propiedad.getUsuario().getNumeroIdentificacion(),
								     propiedad.getUsuario().getNombres(),
									 propiedad.getUsuario().getApellidos(),
									 propiedad.getUsuario().getDireccion(),
									 propiedad.getUsuario().getTelefono(),
									 propiedad.getUsuario().getEmail(),
									 propiedad.getUsuario().getPassword(),
									 new TipoIdentificacionDTO(propiedad.getUsuario().getTipoIdentificacion().getId(),
											 				   propiedad.getUsuario().getTipoIdentificacion().getCodigo(),
															   propiedad.getUsuario().getTipoIdentificacion().getDescripcion()
															   )
									)
					)
			);

		}
		return listPropiedadDto;
	}
	
	@DeleteMapping("/propiedad/{id}")
	public Map<String, String> eliminarPropiedad(@PathVariable Long id) {
		Map<String, String> response = new HashMap<>();

		try {
			propiedadRepository.deleteById(id);
			response.put(Constantes.CODIGO_HTTP, "200");
			response.put(Constantes.MENSAJE_EXITO, "Propiedad eliminada exitosamente");
			return response;
		} catch (Exception e) {
			response.put(Constantes.CODIGO_HTTP, "500");
			response.put(Constantes.MENSAJE_ERROR, "Ocurrió un problema al eliminar la propiedad");
			return response;
		}
	}
	
	@PutMapping("/propiedad/{id}")
	public Map<String, String> actualizarPropiedad(@RequestBody PropiedadDTO newUser, @PathVariable Long id) {
	
		Map<String, String> response = new HashMap<>();

		try {			
			Propiedad propiedad = propiedadRepository.findById(id).get();

			if (newUser.getArea() != null) {
				propiedad.setArea(newUser.getArea());
			}

			if (newUser.getNumeroBanios() != null) {
				propiedad.setNumeroBanios(newUser.getNumeroBanios());
			}

			if (newUser.getNumeroHabitaciones() != null) {
				propiedad.setNumeroHabitaciones(newUser.getNumeroHabitaciones());
			}
			
			if (newUser.getTipoContrato() != null) {
				propiedad.setTipocontrato(newUser.getTipoContrato());
			}
			
			if (newUser.getValor() != null) {
				propiedad.setValor(newUser.getValor());
			}
			
			if (newUser.getUsuarioDTO().getNumeroIdentificacion() != null) {
				propiedad.setUsuario(new Usuario(newUser.getUsuarioDTO().getNumeroIdentificacion(),
								     newUser.getUsuarioDTO().getNombres(),
									 newUser.getUsuarioDTO().getApellidos(),
									 newUser.getUsuarioDTO().getDireccion(),
									 newUser.getUsuarioDTO().getTelefono(),
									 newUser.getUsuarioDTO().getEmail(),
									 newUser.getUsuarioDTO().getPassword(),
									 new TipoIdentificacion(newUser.getUsuarioDTO().getTipoIdentificacionDTO().getId(),
											 				newUser.getUsuarioDTO().getTipoIdentificacionDTO().getCodigo(),
															newUser.getUsuarioDTO().getTipoIdentificacionDTO().getDescripcion()
															   )
									));
			}
			
			
		    propiedadRepository.save(propiedad);

			response.put(Constantes.CODIGO_HTTP, "200");
			response.put(Constantes.MENSAJE_EXITO, "Propiedad actualizada exitosamente");
			return response;
			
			
		} catch (Exception e) {
			response.put(Constantes.CODIGO_HTTP, "500");
			response.put(Constantes.MENSAJE_ERROR, "Ocurrió un problema al actualizar la propiedad");
			return response;
		}

	}

}
