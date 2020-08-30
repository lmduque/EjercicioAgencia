package co.com.udem.ejercicioagencia.util;

import java.text.ParseException;
import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.udem.ejercicioagencia.dto.TipoIdentificacionDTO;
import co.com.udem.ejercicioagencia.entities.TipoIdentificacion;

public class ConvertTipoIdentificacion {
	@Autowired
	private ModelMapper modelMapper;

	public TipoIdentificacion convertToEntity(TipoIdentificacionDTO tipoIdentificacionDTO) throws ParseException {
		return modelMapper.map(tipoIdentificacionDTO, TipoIdentificacion.class);
	}

	public TipoIdentificacionDTO convertToDTO(TipoIdentificacion tipoIdentificacion) throws ParseException {
		return modelMapper.map(tipoIdentificacion, TipoIdentificacionDTO.class);
	}

	public Iterable<TipoIdentificacionDTO> listConvertToDTO(Iterable<TipoIdentificacion> entity) throws ParseException {
		return Arrays.asList(modelMapper.map(entity, TipoIdentificacionDTO.class));
	}

}
