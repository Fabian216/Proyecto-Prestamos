package proyecto.API_Finanzas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.API_Finanzas.dto.ClienteResponse;
import proyecto.API_Finanzas.models.Cliente;
import proyecto.API_Finanzas.repositories.ClienteRepository;

import java.util.ArrayList;
import java.util.List;
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

    public List<ClienteResponse> listarClientesDTO() {
        Iterable<Cliente> clientes = clienteRepository.findAll();
        List<ClienteResponse> respuesta = new ArrayList<>();

        for (Cliente c : clientes) {
            ClienteResponse dto = new ClienteResponse();
            dto.setId(c.getId());
            dto.setDni(c.getDni());
            dto.setNombres(c.getNombres());
            dto.setApellidos(c.getApellidos());
            dto.setEmail(c.getEmail());
            dto.setCelular(c.getCelular());
            respuesta.add(dto);
        }

        return respuesta;
    }

}
