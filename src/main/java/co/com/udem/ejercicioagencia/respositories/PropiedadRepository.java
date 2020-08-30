package co.com.udem.ejercicioagencia.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.com.udem.ejercicioagencia.entities.Propiedad;

public interface PropiedadRepository extends CrudRepository<Propiedad, Long> {
	@Query(value = "SELECT * FROM propiedad p "+
            "where 1=1 "
            +"and exists(select 'x' from usuario u "
            +" where p.usuario_fk=u.numero_identificacion "
            +"and u.numero_identificacion =:identificacion) "
            +"and p.valor >=  COALESCE(:precio,p.valor) "
            +"and  p.numero_habitaciones = COALESCE(:numeroHabitaciones,p.numero_habitaciones) "
            +"and p.area=COALESCE(:area, p.area)",nativeQuery = true)
	public List<Propiedad> consultarPropiedadesUsuario(@Param("identificacion") Long identificacion , @Param("precio")  Double precio, @Param("numeroHabitaciones") Integer numeroHabitaciones,@Param("area") String area);

	@Query(value = "SELECT * FROM propiedad p "+
            "where 1=1 "
            +"and exists(select 'x' from usuario u "
            +" where p.usuario_fk=u.numero_identificacion "
            +"and u.numero_identificacion =:identificacion) "
            ,nativeQuery = true)
	public List<Propiedad> consultarPropiedadesUsuario(@Param("identificacion") Long identificacion );
	
}
