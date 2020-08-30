package co.com.udem.ejercicioagencia.dto;

public class AutenticationResponseDTO {
	private String username;
	private Long numeroIdentificacion;
	private String token;

	

	public AutenticationResponseDTO(String username, Long numeroIdentificacion, String token) {
		super();
		this.username = username;
		this.numeroIdentificacion = numeroIdentificacion;
		this.token = token;
	}

	public AutenticationResponseDTO() {
		super();		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(Long numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}
	
	

}

