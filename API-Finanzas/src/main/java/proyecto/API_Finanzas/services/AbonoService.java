package proyecto.API_Finanzas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.API_Finanzas.models.Abono;
import proyecto.API_Finanzas.repositories.AbonoRepository;

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

    public void eliminarAbono(Integer id) {
        abonoRepository.deleteById(id);
    }

}
