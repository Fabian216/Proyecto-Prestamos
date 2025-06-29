package proyecto.API_Finanzas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import proyecto.API_Finanzas.dto.AbonoRequest;
import proyecto.API_Finanzas.dto.AbonoResponse;
import proyecto.API_Finanzas.models.Abono;
import proyecto.API_Finanzas.models.Prestamo;
import proyecto.API_Finanzas.services.AbonoService;
import proyecto.API_Finanzas.services.PrestamoService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/abonos")
public class AbonoController {

    @Autowired
    private AbonoService abonoService;

    @Autowired
    private PrestamoService prestamoService;

    @GetMapping
    public Iterable<Abono> listarAbonos() {
        return abonoService.listarAbonos();
    }

    @GetMapping("/{id}")
    public Optional<Abono> obtenerPorId(@PathVariable Integer id) {
        return abonoService.obtenerAbonoPorId(id);
    }

    @GetMapping("/prestamo/{prestamoId}")
    public List<AbonoResponse> listarPorPrestamo(@PathVariable Integer prestamoId) {
        List<Abono> abonos = abonoService.listarPorPrestamoId(prestamoId);

        return abonos.stream().map(abono -> {
            AbonoResponse dto = new AbonoResponse();
            dto.setId(abono.getId());
            dto.setBanco(abono.getBanco());
            dto.setMonto(abono.getMonto());
            dto.setFechaAbono(abono.getFechaAbono());
            return dto;
        }).toList();
    }

    @PostMapping
    public Abono registrar(@RequestBody AbonoRequest request) {
        Prestamo prestamo = new Prestamo();
        prestamo.setId(request.getPrestamoId());

        Abono abono = new Abono();
        abono.setBanco(request.getBanco());
        abono.setMonto(request.getMonto());
        abono.setFechaAbono(request.getFechaAbono());
        abono.setPrestamo(prestamo); // solo seteamos el ID

        return abonoService.guardarAbonoConActualizacion(abono);
    }

    @PutMapping("/{id}")
    public Abono actualizar(@PathVariable Integer id, @RequestBody Abono abono) {
        abono.setId(id);
        return abonoService.guardarAbono(abono);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        abonoService.eliminarAbono(id);
    }

}
