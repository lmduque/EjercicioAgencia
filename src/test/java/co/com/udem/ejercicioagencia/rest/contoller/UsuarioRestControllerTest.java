package co.com.udem.ejercicioagencia.rest.contoller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import co.com.udem.ejercicioagencia.EjercicioagenciaApplication;
import co.com.udem.ejercicioagencia.dto.UsuarioDTO;
import co.com.udem.ejercicioagencia.entities.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EjercicioagenciaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioRestControllerTest {
	
	@Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }
    
    @Test
    public void adicionarUsuarioTest() {
    	UsuarioDTO usuarioDTO = new UsuarioDTO();
    	usuarioDTO.setNombres("Pepito");
    	usuarioDTO.setApellidos("Perez");
    	usuarioDTO.setTipoIdentificacion("CC");
    	usuarioDTO.setNumeroIdentificacion((long) 123456789);
    	usuarioDTO.setDireccion("carrera 50 #51-12");
    	usuarioDTO.setTelefono((long) 2607578);
    	usuarioDTO.setEmail("pepito.perez@gmail.com");
    	usuarioDTO.setPassword("123456789");
    	ResponseEntity<UsuarioDTO> postResponse = restTemplate.postForEntity(getRootUrl() + "/usuarios/adicionarUsuario", usuarioDTO, UsuarioDTO.class);    	
    	assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }
    
    @Test
    public void adicionarUsuarioTest2() {
    	UsuarioDTO usuarioDTO = new UsuarioDTO();
    	usuarioDTO.setNombres("Anita");
    	usuarioDTO.setApellidos("Mendoza");
    	usuarioDTO.setTipoIdentificacion("CC");
    	usuarioDTO.setNumeroIdentificacion((long) 123456789);
    	usuarioDTO.setDireccion("carrera 50 #51-12");
    	usuarioDTO.setTelefono((long) 2607578);
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
    	usuarioDTO.setTipoIdentificacion("CC");
    	usuarioDTO.setNumeroIdentificacion((long) 39451241);
    	usuarioDTO.setDireccion("circular 2 #68-31");
    	usuarioDTO.setTelefono((long) 2307578);
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
    
    

}
