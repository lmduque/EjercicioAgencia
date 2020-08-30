package co.com.udem.ejercicioagencia.util;

import java.text.ParseException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.udem.ejercicioagencia.dto.UsuarioDTO;
import co.com.udem.ejercicioagencia.entities.Usuario;

public class ConvertUsuario {
	@Autowired
	private ModelMapper modelMapper;
	
	public Usuario convertToEntity(UsuarioDTO usuarioDTO) throws ParseException {
        return modelMapper.map(usuarioDTO, Usuario.class);
    }
	
	public UsuarioDTO convertToDTO(Usuario usuario) throws ParseException  {
        return modelMapper.map(usuario, UsuarioDTO.class);            
    }
}
