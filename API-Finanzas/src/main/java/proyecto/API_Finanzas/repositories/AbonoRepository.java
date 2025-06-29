package proyecto.API_Finanzas.repositories;

import org.springframework.data.repository.CrudRepository;
import proyecto.API_Finanzas.models.Abono;

import java.util.List;

public interface AbonoRepository extends CrudRepository<Abono, Integer> {
    List<Abono> findByPrestamoId(Integer prestamoId);
}
