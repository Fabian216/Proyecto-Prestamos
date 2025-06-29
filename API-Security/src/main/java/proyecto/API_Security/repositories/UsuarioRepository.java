package proyecto.API_Security.repositories;

import org.springframework.data.repository.CrudRepository;
import proyecto.API_Security.models.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    Optional<Usuario> findByUserName(String userName);
}
