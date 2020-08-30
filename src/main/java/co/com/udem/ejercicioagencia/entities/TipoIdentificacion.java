package co.com.udem.ejercicioagencia.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_identificacion")
public class TipoIdentificacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique=true)
	private String codigo;
	@Column(nullable = false)
	private String descripcion;

	/*
	 * @OneToMany(mappedBy = "tipoIdentificacion") private List<Usuario> usuario;
	 */

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_documento_fk")
	private Set<Usuario> usuario;

	public TipoIdentificacion(Long id, String codigo, String descripcion) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoIdentificacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
