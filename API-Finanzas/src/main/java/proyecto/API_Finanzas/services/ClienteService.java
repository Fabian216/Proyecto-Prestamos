package proyecto.API_Finanzas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.API_Finanzas.models.Cliente;
import proyecto.API_Finanzas.repositories.ClienteRepository;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Iterable<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> obtenerClientePorId(Integer id) {
        return clienteRepository.findById(id);
    }

    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Integer id) {
        clienteRepository.deleteById(id);
    }

}
