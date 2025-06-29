package proyecto.API_Inicio.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proyecto.API_Inicio.clients.ClienteClient;
import proyecto.API_Inicio.dto.ClienteRequest;
import proyecto.API_Inicio.dto.ClienteResponse;

import java.util.List;

@Controller
@RequestMapping("/inicio/clientes")
public class ClienteVistaController {
    @Autowired
    private ClienteClient clienteClient;

    @GetMapping
    public String listarClientes(Model model, HttpSession session) {
        String token = (String) session.getAttribute("jwt");
        if (token == null) {
            return "redirect:/inicio/login?sessionExpired=true";
        }

        List<ClienteResponse> clientes = clienteClient.obtenerClientes();
        model.addAttribute("clientes", clientes);
        return "clientes";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model, HttpSession session) {
        String token = (String) session.getAttribute("jwt");
        if (token == null) {
            return "redirect:/inicio/login?sessionExpired=true";
        }

        model.addAttribute("clienteRequest", new ClienteRequest());
        return "clientes-registro";
    }

    @PostMapping("/crear")
    public String crearCliente(@ModelAttribute ClienteRequest clienteRequest,
                               HttpSession session, Model model) {
        String token = (String) session.getAttribute("jwt");
        if (token == null) {
            return "redirect:/inicio/login?sessionExpired=true";
        }

        try {
            clienteClient.registrarCliente(clienteRequest);
            return "redirect:/inicio/clientes";
        } catch (Exception e) {
            model.addAttribute("error", "Error al registrar cliente");
            return "clientes-registro";
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model, HttpSession session) {
        String token = (String) session.getAttribute("jwt");
        if (token == null) {
            return "redirect:/inicio/login?sessionExpired=true";
        }

        List<ClienteResponse> clientes = clienteClient.obtenerClientes();
        ClienteResponse cliente = clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (cliente == null) {
            return "redirect:/inicio/clientes?error=notfound";
        }

        ClienteRequest clienteRequest = new ClienteRequest();
        clienteRequest.setDni(cliente.getDni());
        clienteRequest.setNombres(cliente.getNombres());
        clienteRequest.setApellidos(cliente.getApellidos());
        clienteRequest.setEmail(cliente.getEmail());
        clienteRequest.setCelular(cliente.getCelular());

        model.addAttribute("clienteRequest", clienteRequest);
        model.addAttribute("clienteId", id);
        return "clientes-editar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarCliente(@PathVariable Integer id,
                                    @ModelAttribute ClienteRequest clienteRequest,
                                    HttpSession session, Model model) {
        String token = (String) session.getAttribute("jwt");
        if (token == null) {
            return "redirect:/inicio/login?sessionExpired=true";
        }

        try {
            clienteClient.actualizarCliente(id, clienteRequest);
            return "redirect:/inicio/clientes";
        } catch (Exception e) {
            model.addAttribute("error", "Error al actualizar cliente");
            return "clientes-editar";
        }
    }

}
