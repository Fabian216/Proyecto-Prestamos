package proyecto.API_Finanzas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import proyecto.API_Finanzas.models.Cliente;
import proyecto.API_Finanzas.services.ClienteService;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public Iterable<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public Optional<Cliente> obtenerPorId(@PathVariable Integer id) {
        return clienteService.obtenerClientePorId(id);
    }

    @PostMapping
    public Cliente registrar(@RequestBody Cliente cliente) {
        return clienteService.guardarCliente(cliente);
    }

    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable Integer id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        return clienteService.guardarCliente(cliente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        clienteService.eliminarCliente(id);
    }

}
