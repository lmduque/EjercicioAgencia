package co.com.udem.ejercicioagencia;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import co.com.udem.ejercicioagencia.util.ConvertUsuario;

@SpringBootApplication
public class EjercicioagenciaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EjercicioagenciaApplication.class, args);
	}
	
	@Bean
	public ConvertUsuario convertUsuario() {
		return new ConvertUsuario(); 		
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper(); 		
	}

}
