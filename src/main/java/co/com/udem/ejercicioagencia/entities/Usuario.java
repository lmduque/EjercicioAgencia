package co.com.udem.ejercicioagencia.entities;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long numeroIdentificacion;
	@Column(nullable = false)
	private String nombres;
	//@Column(nullable = false)
	private String apellidos;
	//@Column(nullable = false)
	private String direccion;
	private String telefono;
	@Column(nullable = false,unique=true)
	private String email;
	@Column(nullable = false)
	private String password;

	/*@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "tipoDocumento_fk", referencedColumnName = "codigo")
	private TipoIdentificacion tipoIdentificacion;*/
	
	@ManyToOne
	@JoinColumn(name="tipo_documento_fk")	  
	private TipoIdentificacion tipoIdentificacion;

	/*@OneToMany(mappedBy = "usuario")
	private List<Propiedad> propiedad;*/
	
	@OneToMany
	@JoinColumn(name="usuario_fk")	  
	private Set<Propiedad> propieadad;

	@ElementCollection(fetch = FetchType.EAGER)
	@Builder.Default
	private List<String> roles = new ArrayList<>();

	public Usuario(Long numeroIdentificacion, String nombres, String apellidos, String direccion, String telefono,
			String email, String password, TipoIdentificacion tipoIdentificacion) {
		super();
		this.numeroIdentificacion = numeroIdentificacion;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.password = password;
		this.tipoIdentificacion = tipoIdentificacion;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * public Usuario() { super(); // TODO Auto-generated constructor stub }
	 */

	/*
	 * public Long getNumeroIdentificacion() { return numeroIdentificacion; }
	 * 
	 * public void setNumeroIdentificacion(Long numeroIdentificacion) {
	 * this.numeroIdentificacion = numeroIdentificacion; }
	 * 
	 * public String getNombres() { return nombres; }
	 * 
	 * public void setNombres(String nombres) { this.nombres = nombres; }
	 * 
	 * public String getApellidos() { return apellidos; }
	 * 
	 * public void setApellidos(String apellidos) { this.apellidos = apellidos; }
	 * 
	 * public String getDireccion() { return direccion; }
	 * 
	 * public void setDireccion(String direccion) { this.direccion = direccion; }
	 * 
	 * public String getTelefono() { return telefono; }
	 * 
	 * public void setTelefono(String telefono) { this.telefono = telefono; }
	 * 
	 * public String getEmail() { return email; }
	 * 
	 * public void setEmail(String email) { this.email = email; }
	 * 
	 * public void setPassword(String password) { this.password = password; }
	 * 
	 * public TipoIdentificacion getTipoIdentificacion() { return
	 * tipoIdentificacion; }
	 * 
	 * public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
	 * this.tipoIdentificacion = tipoIdentificacion; }
	 */

}
