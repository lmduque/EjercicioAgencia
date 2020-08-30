package co.com.udem.ejercicioagencia.security.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import co.com.udem.ejercicioagencia.respositories.UsuarioRepository;


@Component
public class CustomUserDetailsService implements UserDetailsService {

    private UsuarioRepository users;

    public CustomUserDetailsService(UsuarioRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.users.findByEmail( username )
            .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }
}
