package proyecto.API_Inicio.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import proyecto.API_Inicio.dto.PrestamoRequest;
import proyecto.API_Inicio.dto.PrestamoResponse;

import java.util.List;

@FeignClient(name = "API-Finanzas", contextId = "prestamoClient")
public interface PrestamoClient {
    @GetMapping("/prestamos/dto")
    List<PrestamoResponse> obtenerPrestamosConCliente();

    @PostMapping("/prestamos")
    PrestamoResponse crearPrestamo(@RequestBody PrestamoRequest request);
}
