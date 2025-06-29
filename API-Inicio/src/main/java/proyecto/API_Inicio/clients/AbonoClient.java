package proyecto.API_Inicio.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import proyecto.API_Inicio.dto.AbonoResponse;

import java.util.List;

@FeignClient(name = "API-Finanzas", contextId = "abonoClient")
public interface AbonoClient {
    @GetMapping("/abonos/prestamo/{prestamoId}")
    List<AbonoResponse> obtenerAbonosPorPrestamo(@PathVariable("prestamoId") Integer prestamoId);
}
