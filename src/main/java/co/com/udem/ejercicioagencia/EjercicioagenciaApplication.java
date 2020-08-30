package co.com.udem.ejercicioagencia;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import co.com.udem.ejercicioagencia.util.ConvertPropiedad;
import co.com.udem.ejercicioagencia.util.ConvertTipoIdentificacion;
import co.com.udem.ejercicioagencia.util.ConvertUsuario;

@SpringBootApplication
@EnableDiscoveryClient
public class EjercicioagenciaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EjercicioagenciaApplication.class, args);
		//.
	}
	
	@Bean
	public ConvertUsuario convertUsuario() {
		return new ConvertUsuario(); 		
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper(); 		
	}
	
	@Bean
	public ConvertTipoIdentificacion convertTipoIdentificacion() {
		return new ConvertTipoIdentificacion(); 		
	}
	
	@Bean
	public ConvertPropiedad convertPropiedad() {
		return new ConvertPropiedad(); 		
	}
	
	@Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

}
