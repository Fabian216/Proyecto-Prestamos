package proyecto.API_Finanzas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import proyecto.API_Finanzas.dto.PrestamoRequest;
import proyecto.API_Finanzas.dto.PrestamoResponse;
import proyecto.API_Finanzas.models.Cliente;
import proyecto.API_Finanzas.models.Prestamo;
import proyecto.API_Finanzas.services.PrestamoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @GetMapping
    public Iterable<Prestamo> listarPrestamos() {
        return prestamoService.listarPrestamos();
    }

    @GetMapping("/{id}")
    public Optional<Prestamo> obtenerPorId(@PathVariable Integer id) {
        return prestamoService.obtenerPrestamoPorId(id);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Prestamo> listarPorCliente(@PathVariable Integer clienteId) {
        return prestamoService.listarPorClienteId(clienteId);
    }

    @PostMapping
    public Prestamo registrar(@RequestBody PrestamoRequest request) {
        Cliente cliente = new Cliente();
        cliente.setId(request.getClienteId());

        Prestamo prestamo = new Prestamo();
        prestamo.setMonto(request.getMonto());
        prestamo.setFechaPrestamo(request.getFechaPrestamo());
        prestamo.setCliente(cliente); // solo seteamos el id

        return prestamoService.guardarPrestamo(prestamo);
    }


    @PutMapping("/{id}")
    public Prestamo actualizar(@PathVariable Integer id, @RequestBody Prestamo prestamo) {
        prestamo.setId(id);
        return prestamoService.guardarPrestamo(prestamo);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        prestamoService.eliminarPrestamo(id);
    }

    @GetMapping("/dto")
    public List<PrestamoResponse> listarDTO() {
        return prestamoService.listarPrestamosDTO();
    }


}
