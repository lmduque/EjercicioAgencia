package co.com.udem.ejercicioagencia.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.beans.factory.annotation.Autowired;

import co.com.udem.ejercicioagencia.util.TipoContrato;

public class PropiedadDTO {

	private Long id;
	private Long area;
	private Integer numeroHabitaciones;
	private Long numeroBanios;
	@Enumerated(EnumType.STRING)
	private TipoContrato tipoContrato;
	private Double valor;
	@Autowired
	private UsuarioDTO usuarioDTO;

	public PropiedadDTO(Long id, Long area, Integer numeroHabitaciones, Long numeroBanios, TipoContrato tipoContrato,
			Double valor, UsuarioDTO usuarioDTO) {
		super();
		this.id = id;
		this.area = area;
		this.numeroHabitaciones = numeroHabitaciones;
		this.numeroBanios = numeroBanios;
		this.tipoContrato = tipoContrato;
		this.valor = valor;
		this.usuarioDTO = usuarioDTO;
	}

	public PropiedadDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getArea() {
		return area;
	}

	public void setArea(Long area) {
		this.area = area;
	}

	public Integer getNumeroHabitaciones() {
		return numeroHabitaciones;
	}

	public void setNumeroHabitaciones(Integer numeroHabitaciones) {
		this.numeroHabitaciones = numeroHabitaciones;
	}

	public Long getNumeroBanios() {
		return numeroBanios;
	}

	public void setNumeroBanios(Long numeroBanios) {
		this.numeroBanios = numeroBanios;
	}

	public TipoContrato getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(TipoContrato tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

}
