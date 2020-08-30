package co.com.udem.ejercicioagencia.rest.contoller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
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

import co.com.udem.ejercicioagencia.dto.TipoIdentificacionDTO;
import co.com.udem.ejercicioagencia.entities.TipoIdentificacion;
import co.com.udem.ejercicioagencia.respositories.TipoIdentificacionRepository;
import co.com.udem.ejercicioagencia.util.Constantes;
import co.com.udem.ejercicioagencia.util.ConvertTipoIdentificacion;

@RestController
public class TipoIdentificacionRestController {
	private static final Logger logger = LogManager.getLogger(UsuarioRestController.class);
	
	@Autowired
	private TipoIdentificacionRepository tipoIdentificacionRepository;
	
	@Autowired
	private ConvertTipoIdentificacion convertTipoIdentificacion;
	
	@PostMapping("/tipoIdentificacion/ingresar")
	public Map<String, String> ingresarTipoIdentificacion(@RequestBody TipoIdentificacionDTO tipoIdentificacionDTO) {
		Map<String, String> response = new HashMap<>();
		try {
			TipoIdentificacion tipoIdentificacion = convertTipoIdentificacion.convertToEntity(tipoIdentificacionDTO);
			tipoIdentificacionRepository.save(tipoIdentificacion);
			response.put(Constantes.CODIGO_HTTP, "200");
			response.put(Constantes.MENSAJE_EXITO, "Tipo de identificacion ingresado exitosamente");
			return response;
		} catch (ParseException e) {
			response.put(Constantes.CODIGO_HTTP, "500");
			response.put(Constantes.MENSAJE_ERROR, "Ocurrió un problema al ingresar el tipo de identificación");
			return response;
		}
	}
	
	@GetMapping("/tipoIdentificacion/{id}")
	public TipoIdentificacionDTO buscarTipoDocumento(@PathVariable Long id) {
		TipoIdentificacionDTO tipoIdentificacionDTO = null;
		TipoIdentificacion tipoIdentificacion;
		try {
			tipoIdentificacion = tipoIdentificacionRepository.findById(id).get();
			tipoIdentificacionDTO = convertTipoIdentificacion.convertToDTO(tipoIdentificacion);
		} catch (ParseException e) {
			BasicConfigurator.configure();
			logger.info(e);

		}
		return tipoIdentificacionDTO;

	}
	
	@DeleteMapping("/tipoIdentificacion/{id}")
	public Map<String, String> eliminarTipoDocumento(@PathVariable Long id) {
		Map<String, String> response = new HashMap<>();

		try {
			tipoIdentificacionRepository.deleteById(id);
			response.put(Constantes.CODIGO_HTTP, "200");
			response.put(Constantes.MENSAJE_EXITO, "Tipo de documento eliminado exitosamente");
			return response;
		} catch (Exception e) {
			response.put(Constantes.CODIGO_HTTP, "500");
			response.put(Constantes.MENSAJE_ERROR, "Ocurrió un problema al eliminar el tipo de documento");
			return response;
		}
	}
	
	@GetMapping("/tipoIdentificacion")
	public Iterable<TipoIdentificacionDTO> listarTiposIdentificacion() {
		
		List<TipoIdentificacionDTO> listTipoIdentificacionDTO = new ArrayList<>();		
		
		for (TipoIdentificacion tipoIdentificacion : tipoIdentificacionRepository.findAll()) {			
			listTipoIdentificacionDTO.add(new TipoIdentificacionDTO(tipoIdentificacion.getId(), tipoIdentificacion.getCodigo(),tipoIdentificacion.getDescripcion()));

		}
		
		return listTipoIdentificacionDTO;
	}
		
		
	@PutMapping("/tipoIdentificacion/{id}")
	public Map<String, String> actualizarTipoIdentificacion(@RequestBody TipoIdentificacionDTO newTipoIdentificacion, @PathVariable Long id) {
		Map<String, String> response = new HashMap<>();

		try {			
			
			TipoIdentificacion tipoIdentificacion = tipoIdentificacionRepository.findById(id).get();

			if (newTipoIdentificacion.getCodigo() != null) {
				tipoIdentificacion.setCodigo(newTipoIdentificacion.getCodigo());
			}
			
			if (newTipoIdentificacion.getDescripcion() != null) {
				tipoIdentificacion.setDescripcion(newTipoIdentificacion.getDescripcion());
			}
			
			tipoIdentificacionRepository.save(tipoIdentificacion);

			response.put(Constantes.CODIGO_HTTP, "200");
			response.put(Constantes.MENSAJE_EXITO, "Tipo de Identificación actualizado exitosamente");
			return response;
		} catch (Exception e) {
			response.put(Constantes.CODIGO_HTTP, "500");
			response.put(Constantes.MENSAJE_ERROR, "Ocurrió un problema al actualizar el Tipo de Identificación");
			return response;
		}

	}

}
