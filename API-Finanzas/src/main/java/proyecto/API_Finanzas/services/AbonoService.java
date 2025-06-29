package proyecto.API_Finanzas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.API_Finanzas.models.Abono;
import proyecto.API_Finanzas.models.Prestamo;
import proyecto.API_Finanzas.repositories.AbonoRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AbonoService {

    @Autowired
    private AbonoRepository abonoRepository;

    public Iterable<Abono> listarAbonos() {
        return abonoRepository.findAll();
    }

    public Optional<Abono> obtenerAbonoPorId(Integer id) {
        return abonoRepository.findById(id);
    }

    public List<Abono> listarPorPrestamoId(Integer prestamoId) {
        return abonoRepository.findByPrestamoId(prestamoId);
    }

    public Abono guardarAbono(Abono abono) {
        return abonoRepository.save(abono);
    }

    @Autowired
    private PrestamoService prestamoService;

    public Abono guardarAbonoConActualizacion(Abono abono) {
        // Obtener el préstamo original desde BD
        Integer prestamoId = abono.getPrestamo().getId();
        Optional<Prestamo> optionalPrestamo = prestamoService.obtenerPrestamoPorId(prestamoId);

        if (optionalPrestamo.isEmpty()) {
            throw new RuntimeException("Préstamo no encontrado");
        }

        Prestamo prestamo = optionalPrestamo.get();

        // Registrar abono
        Abono abonoGuardado = abonoRepository.save(abono);

        // Actualizar el campo restante del préstamo
        BigDecimal nuevoRestante = prestamo.getRestante().subtract(abono.getMonto());
        prestamo.setRestante(nuevoRestante.max(BigDecimal.ZERO)); // Evita negativos

        prestamoService.guardarPrestamo(prestamo); // Actualiza en BD

        return abonoGuardado;
    }

    public void eliminarAbono(Integer id) {
        abonoRepository.deleteById(id);
    }

}
