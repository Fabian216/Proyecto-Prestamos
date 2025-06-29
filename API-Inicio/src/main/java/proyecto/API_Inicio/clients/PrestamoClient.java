package proyecto.API_Inicio.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import proyecto.API_Inicio.dto.PrestamoResponse;

import java.util.List;

@FeignClient(name = "API-Finanzas", contextId = "prestamoClient")
public interface PrestamoClient {
    @GetMapping("/prestamos/dto")
    List<PrestamoResponse> obtenerPrestamosConCliente();
}
