package co.com.udem.ejercicioagencia.rest.contoller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;

import co.com.udem.ejercicioagencia.EjercicioagenciaApplication;
import co.com.udem.ejercicioagencia.dto.AutenticationRequestDTO;
import co.com.udem.ejercicioagencia.dto.AutenticationResponseDTO;
import co.com.udem.ejercicioagencia.dto.PropiedadDTO;
import co.com.udem.ejercicioagencia.dto.TipoIdentificacionDTO;
import co.com.udem.ejercicioagencia.dto.UsuarioDTO;
import co.com.udem.ejercicioagencia.util.TipoContrato;
@RunWith(JUnitPlatform.class)
//@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = EjercicioagenciaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PropiedadRestControllerTest {
	
	@Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;
    
    
    
    
    private String getRootUrl() {
        return "http://localhost:" + port;
    }
    
   
    @Test
    @Order(1)    
    public void adicionarTipoIdentificacionTest() {
    	TipoIdentificacionDTO tipoIdentificacionDTO = new TipoIdentificacionDTO();
    	tipoIdentificacionDTO.setCodigo("CC");	
    	tipoIdentificacionDTO.setDescripcion("Cedula de ciudadania");
    	ResponseEntity<TipoIdentificacionDTO> postResponse = restTemplate.postForEntity(getRootUrl() + "/tipoIdentificacion/ingresar", tipoIdentificacionDTO, TipoIdentificacionDTO.class);    	
    	assertEquals(200, postResponse.getStatusCode().value());
    }

    //@BeforeClass
    @Test
    @Order(2)    
    public void ingresarUsuarioTest() {
    	//AutenticationRequestDTO autenticationRequestDTO = new AutenticationRequestDTO();
        ResponseEntity<String> postResponse = restTemplate.postForEntity(getRootUrl() + "/usuario/ingresarUsuario", getUsuarioDTO(), String.class);
        assertEquals(200, postResponse.getStatusCode().value());
    }

    private UsuarioDTO getUsuarioDTO() {
    	TipoIdentificacionDTO tipoIdentificacionDTO;
    	long idTipoIdent = 1;
    	long numeroIdentificacion = 39451241;
    	tipoIdentificacionDTO = new TipoIdentificacionDTO(idTipoIdent,"CC","Cedula de Ciudadania");
    	
    	UsuarioDTO usuarioDTO = new UsuarioDTO();    	
    	usuarioDTO.setNumeroIdentificacion(numeroIdentificacion);
    	usuarioDTO.setNombres("Lina Maria");
    	usuarioDTO.setApellidos("Duque Tobon");
    	usuarioDTO.setDireccion("San Joaquin");
    	usuarioDTO.setEmail("lmduque@gmail.com"); 
    	usuarioDTO.setPassword("123456789");
    	usuarioDTO.setTelefono("1111111");
    	usuarioDTO.setTipoIdentificacionDTO(tipoIdentificacionDTO);
    	return usuarioDTO;
	}
    
    public String getToken() {
    	AutenticationRequestDTO autenticationRequestDTO = new AutenticationRequestDTO();
   	
    	UsuarioDTO usuarioDTO = getUsuarioDTO() ;
        Gson g = new Gson();

        autenticationRequestDTO.setUsername(usuarioDTO.getEmail());
        autenticationRequestDTO.setNumeroIdentificacion(usuarioDTO.getNumeroIdentificacion());
        autenticationRequestDTO.setPassword(usuarioDTO.getPassword());
        
        ResponseEntity<String> postResponse = restTemplate.postForEntity(getRootUrl() + "/auth/signin", autenticationRequestDTO, String.class);
        AutenticationResponseDTO autenticationResponseDTO = g.fromJson(postResponse.getBody(), AutenticationResponseDTO.class);
        return autenticationResponseDTO.getToken();
    
    }



	@Test
    @Order(3)    
    public void authorizationTest() {
    	assertNotNull(getToken());    
    }
    
    @Test
    @Order(4)    
    public void adicionarPropiedadTest() {
    	PropiedadDTO propiedadDTO = new PropiedadDTO();
    	propiedadDTO.setArea(100L);
    	propiedadDTO.setNumeroBanios(2L);
    	propiedadDTO.setNumeroHabitaciones(3);
    	propiedadDTO.setValor(1500000D);
    	propiedadDTO.setTipoContrato(TipoContrato.ARRIENDO);
    	propiedadDTO.setUsuarioDTO(getUsuarioDTO());;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String token = getToken();
        System.out.println("El token es: "+token);
        headers.set("Authorization", "Bearer "+token);
        HttpEntity<PropiedadDTO> entity = new HttpEntity<PropiedadDTO>(propiedadDTO, headers);
    	ResponseEntity<String> postResponse = restTemplate.postForEntity(getRootUrl() + "/propiedad/ingresar", entity, String.class);
    	assertEquals(200, postResponse.getStatusCode().value());
    }

    @Test
    @Order(5)    
    public void buscarPropiedadTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String token = getToken();
        System.out.println("El token es: "+token);
        headers.set("Authorization", "Bearer "+token);
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/propiedad/consultar/"+getUsuarioDTO().getNumeroIdentificacion(), HttpMethod.GET, entity,String.class);
    	assertEquals(200, response.getStatusCode().value());
    }    

    
    @Test
    @Order(6)    
    public void adicionarPropiedadTest2() {
    	PropiedadDTO propiedadDTO = new PropiedadDTO();
    	propiedadDTO.setArea(200L);
    	propiedadDTO.setNumeroBanios(2L);
    	propiedadDTO.setNumeroHabitaciones(3);
    	propiedadDTO.setValor(700000000D);
    	propiedadDTO.setTipoContrato(TipoContrato.VENTA);
    	propiedadDTO.setUsuarioDTO(getUsuarioDTO());;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String token = getToken();
        System.out.println("El token es: "+token);
        headers.set("Authorization", "Bearer "+token);
        HttpEntity<PropiedadDTO> entity = new HttpEntity<PropiedadDTO>(propiedadDTO, headers);
    	ResponseEntity<String> postResponse = restTemplate.postForEntity(getRootUrl() + "/propiedad/ingresar", entity, String.class);
    	assertEquals(200, postResponse.getStatusCode().value());
    }
    @Test
    @Order(7)    
    public void actualizarPropiedadTest() {
    	PropiedadDTO propiedadDTO = new PropiedadDTO();
    	propiedadDTO.setValor(800000000D);
    	propiedadDTO.setUsuarioDTO(getUsuarioDTO());;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String token = getToken();
        System.out.println("El token es: "+token);
        headers.set("Authorization", "Bearer "+token);
        HttpEntity<PropiedadDTO> entity = new HttpEntity<PropiedadDTO>(propiedadDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/propiedad/2", HttpMethod.PUT, entity,String.class);
    	assertEquals(200, response.getStatusCode().value());
    }    

    @Test
    @Order(8)    
    public void adicionarPropiedadABorrarTest() {
    	PropiedadDTO propiedadDTO = new PropiedadDTO();
    	propiedadDTO.setArea(50L);
    	propiedadDTO.setNumeroBanios(2L);
    	propiedadDTO.setNumeroHabitaciones(3);
    	propiedadDTO.setValor(650000D);
    	propiedadDTO.setTipoContrato(TipoContrato.ARRIENDO);
    	propiedadDTO.setUsuarioDTO(getUsuarioDTO());;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String token = getToken();
        System.out.println("El token es: "+token);
        headers.set("Authorization", "Bearer "+token);
        HttpEntity<PropiedadDTO> entity = new HttpEntity<PropiedadDTO>(propiedadDTO, headers);
    	ResponseEntity<String> postResponse = restTemplate.postForEntity(getRootUrl() + "/propiedad/ingresar", entity, String.class);
    	assertEquals(200, postResponse.getStatusCode().value());
    }

    @Test
    @Order(9)    
    public void eliminarPropiedadTest() {
    	PropiedadDTO propiedadDTO = new PropiedadDTO();
    	propiedadDTO.setValor(800000000D);
    	propiedadDTO.setUsuarioDTO(getUsuarioDTO());;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String token = getToken();
        System.out.println("El token es: "+token);
        headers.set("Authorization", "Bearer "+token);
        HttpEntity<PropiedadDTO> entity = new HttpEntity<PropiedadDTO>(propiedadDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/propiedad/3", HttpMethod.DELETE, entity,String.class);
    	assertEquals(200, response.getStatusCode().value());
    }    

    /*@Test
    public void adicionarTipoIdentificacionTest() {
    	TipoIdentificacionDTO tipoIdentificacionDTO = new TipoIdentificacionDTO();
    	tipoIdentificacionDTO.setCodigo("CC");	
    	tipoIdentificacionDTO.setDescripcion("Cedula de ciudadania");
    	ResponseEntity<UsuarioDTO> postResponse = restTemplate.postForEntity(getRootUrl() + "/tipoIdentificacion/ingresar", usuarioDTO, UsuarioDTO.class);    	
    	assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }
    
    @Test
    public void adicionarUsuarioTest2() {
    	UsuarioDTO usuarioDTO = new UsuarioDTO();
    	usuarioDTO.setNombres("Anita");
    	usuarioDTO.setApellidos("Mendoza");
    	//usuarioDTO.setTipoIdentificacion("CC");
    	usuarioDTO.setNumeroIdentificacion((long) 123456789);
    	usuarioDTO.setDireccion("carrera 50 #51-12");
    	//usuarioDTO.setTelefono((long) 2607578);
    	usuarioDTO.setEmail("anita.mendoza@gmail.com");
    	usuarioDTO.setPassword("987654321");
    	ResponseEntity<UsuarioDTO> postResponse = restTemplate.postForEntity(getRootUrl() + "/usuarios/adicionarUsuario", usuarioDTO, UsuarioDTO.class);    	
    	assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }
    
    @Test
    public void adicionarUsuarioTest3() {
    	UsuarioDTO usuarioDTO = new UsuarioDTO();
    	usuarioDTO.setNombres("Lina Maria");
    	usuarioDTO.setApellidos("Duque Tobón");
    	//usuarioDTO.setTipoIdentificacion("CC");
    	usuarioDTO.setNumeroIdentificacion((long) 39451241);
    	usuarioDTO.setDireccion("circular 2 #68-31");
    	//usuarioDTO.setTelefono((long) 2307578);
    	usuarioDTO.setEmail("lmduque@gmail.com");
    	usuarioDTO.setPassword("123456789");
    	ResponseEntity<UsuarioDTO> postResponse = restTemplate.postForEntity(getRootUrl() + "/usuarios/adicionarUsuario", usuarioDTO, UsuarioDTO.class);    	
    	assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }
    
    @Test
    public void consultarUsuariosTest() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/usuarios", HttpMethod.GET, entity,String.class);
        assertNotNull(response.getBody());        
    }
    
    @Test
    public void consultarUsuarioPorIdTest() {
        Usuario usuario = restTemplate.getForObject(getRootUrl() + "/usuarios/3", Usuario.class);
        System.out.println(usuario.getNombres());
        System.out.println(usuario.getApellidos());
        System.out.println(usuario.getTipoIdentificacion());
        System.out.println(usuario.getNumeroIdentificacion());
        System.out.println(usuario.getDireccion());
        System.out.println(usuario.getTelefono());
        System.out.println(usuario.getEmail());
        assertNotNull(usuario);
    }
    
    @Test
    public void eliminarUsuarioTest() {
    	int id = 2;
    	Usuario usuario = restTemplate.getForObject(getRootUrl() + "/usuarios/" + id, Usuario.class);
    	assertNotNull(usuario);
        restTemplate.delete(getRootUrl() + "/usuarios/" + id);
        
        try {
            usuario = restTemplate.getForObject(getRootUrl() + "/usuarios/" + id, Usuario.class);
       } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
       }
    }
    
    @Test
    public void testUpdateUsuario() {
        int id = 1;
        Usuario usuario = restTemplate.getForObject(getRootUrl() + "/usuarios/" + id, Usuario.class);
        usuario.setNombres("Maximiliano");
        usuario.setApellidos("Gomez Duque");
        restTemplate.put(getRootUrl() + "/usuarios/" + id, usuario);
        Usuario updatedUsuario = restTemplate.getForObject(getRootUrl() + "/usuarios/" + id, Usuario.class);
        assertNotNull(updatedUsuario);
    }
    */
    

}
