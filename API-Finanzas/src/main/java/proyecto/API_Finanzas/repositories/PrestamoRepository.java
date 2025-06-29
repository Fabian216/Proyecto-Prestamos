package proyecto.API_Finanzas.repositories;

import org.springframework.data.repository.CrudRepository;
import proyecto.API_Finanzas.models.Prestamo;

import java.util.List;

public interface PrestamoRepository extends CrudRepository<Prestamo, Integer> {
    List<Prestamo> findByClienteId(Integer clienteId);
}
