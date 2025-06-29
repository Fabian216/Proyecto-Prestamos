package proyecto.API_Finanzas.repositories;

import org.springframework.data.repository.CrudRepository;
import proyecto.API_Finanzas.models.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
}
