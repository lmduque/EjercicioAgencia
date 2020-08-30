package co.com.udem.ejercicioagencia.dto;

import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioDTO {	
	private Long   numeroIdentificacion;
	private String nombres;
	private String apellidos;	
	private String direccion;
	private String telefono;
	private String email;
	private String password;

	@Autowired
	TipoIdentificacionDTO tipoIdentificacionDTO;

	public UsuarioDTO(Long numeroIdentificacion, String nombres, String apellidos, String direccion, String telefono,
			String email, String password, TipoIdentificacionDTO tipoIdentificacionDTO) {
		super();
		this.numeroIdentificacion = numeroIdentificacion;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.password = password;
		this.tipoIdentificacionDTO = tipoIdentificacionDTO;
	}

	public UsuarioDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(Long numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TipoIdentificacionDTO getTipoIdentificacionDTO() {
		return tipoIdentificacionDTO;
	}

	public void setTipoIdentificacionDTO(TipoIdentificacionDTO tipoIdentificacionDTO) {
		this.tipoIdentificacionDTO = tipoIdentificacionDTO;
	}

	

}
