package proyecto.API_Inicio.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import proyecto.API_Inicio.dto.AuthRequest;
import proyecto.API_Inicio.dto.AuthResponse;

@FeignClient(name = "API-Security")
public interface AuthClient {
    @PostMapping("/auth/login")
    AuthResponse login(@RequestBody AuthRequest request);
}
