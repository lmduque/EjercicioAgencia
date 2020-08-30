package co.com.udem.ejercicioagencia.dto;

public class AutenticationRequestDTO {
	private String username;
	private Long numeroIdentificacion;
	private String password;
	


	public AutenticationRequestDTO(String username, Long numeroIdentificacion, String password) {
		super();
		this.username = username;
		this.numeroIdentificacion = numeroIdentificacion;
		this.password = password;
	}
	
	public AutenticationRequestDTO() {
		super();
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(Long numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}
		

}

