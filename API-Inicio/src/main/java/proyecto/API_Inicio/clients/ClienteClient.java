package proyecto.API_Inicio.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import proyecto.API_Inicio.dto.ClienteRequest;
import proyecto.API_Inicio.dto.ClienteResponse;

import java.util.List;

@FeignClient(name = "API-Finanzas", contextId = "clienteClient")
public interface ClienteClient {

    @GetMapping("/clientes/dto")
    List<ClienteResponse> obtenerClientes();

    @PostMapping("/clientes")
    ClienteResponse registrarCliente(@RequestBody ClienteRequest request);

    @PutMapping("/clientes/{id}")
    ClienteResponse actualizarCliente(@PathVariable("id") Integer id, @RequestBody ClienteRequest request);

}
