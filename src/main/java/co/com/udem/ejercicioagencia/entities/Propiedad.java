package co.com.udem.ejercicioagencia.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.com.udem.ejercicioagencia.util.TipoContrato;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "propiedad")
public class Propiedad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long area;
	private Integer numeroHabitaciones;
	private Long numeroBanios;
	@Enumerated(EnumType.STRING)
	private TipoContrato tipocontrato;
	private Double valor;
	
	@ManyToOne 
	@JoinColumn(name="usuario_fk")	  
	private Usuario usuario;
	
}
