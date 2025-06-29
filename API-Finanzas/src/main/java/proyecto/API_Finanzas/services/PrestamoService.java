package proyecto.API_Finanzas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.API_Finanzas.dto.PrestamoResponse;
import proyecto.API_Finanzas.models.Cliente;
import proyecto.API_Finanzas.models.Prestamo;
import proyecto.API_Finanzas.repositories.PrestamoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    public Iterable<Prestamo> listarPrestamos() {
        return prestamoRepository.findAll();
    }

    public Optional<Prestamo> obtenerPrestamoPorId(Integer id) {
        return prestamoRepository.findById(id);
    }

    public List<Prestamo> listarPorClienteId(Integer clienteId) {
        return prestamoRepository.findByClienteId(clienteId);
    }

    public Prestamo guardarPrestamo(Prestamo prestamo) {
        if (prestamo.getId() == null) {
            prestamo.setRestante(prestamo.getMonto()); // al crear el restante será = monto
        }
        return prestamoRepository.save(prestamo);
    }

    public void eliminarPrestamo(Integer id) {
        prestamoRepository.deleteById(id);
    }

    public List<PrestamoResponse> listarPrestamosDTO() {
        Iterable<Prestamo> prestamos = prestamoRepository.findAll();

        List<PrestamoResponse> respuesta = new ArrayList<>();
        for (Prestamo p : prestamos) {
            PrestamoResponse dto = new PrestamoResponse();
            dto.setId(p.getId());
            dto.setRestante(p.getRestante());
            dto.setMonto(p.getMonto());
            dto.setFechaPrestamo(p.getFechaPrestamo());

            Cliente cliente = p.getCliente();
            if (cliente != null) {
                dto.setClienteId(cliente.getId());
                dto.setClienteDni(cliente.getDni());
                dto.setClienteNombres(cliente.getNombres());
                dto.setClienteApellidos(cliente.getApellidos());
            }

            respuesta.add(dto);
        }

        return respuesta;
    }

}
