package proyecto.API_Security.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import proyecto.API_Security.dto.AuthRequest;
import proyecto.API_Security.dto.AuthResponse;
import proyecto.API_Security.models.Usuario;
import proyecto.API_Security.repositories.UsuarioRepository;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public AuthResponse login(AuthRequest request) {
        Optional<Usuario> optUser = usuarioRepository.findByUserName(request.getUserName());
        if (optUser.isPresent()) {
            Usuario usuario = optUser.get();
            if (passwordEncoder.matches(request.getPassword(), usuario.getPasswordHash())) {
                String token = Jwts.builder()
                        .setSubject(usuario.getUserName())
                        .claim("role", usuario.getUserRole())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + 3600_000)) // 1 hora
                        .signWith(key)
                        .compact();
                return new AuthResponse(token);
            }
        }
        throw new RuntimeException("Usuario o contraseña inválidos");
    }

    public Key getKey() {
        return key;
    }

}
