package proyecto.API_Inicio.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import proyecto.API_Inicio.clients.ClienteClient;
import proyecto.API_Inicio.clients.PrestamoClient;
import proyecto.API_Inicio.dto.PrestamoRequest;
import proyecto.API_Inicio.dto.PrestamoResponse;

import java.util.List;

@Controller
@RequestMapping("/inicio/prestamos")
public class PrestamoVistaController {
    @Autowired
    private PrestamoClient prestamoClient;

    @Autowired
    private ClienteClient clienteClient;

    @GetMapping
    public String listarPrestamos(Model model, HttpSession session) {
        String token = (String) session.getAttribute("jwt");
        if (token == null) {
            return "redirect:/inicio/login?sessionExpired=true";
        }

        List<PrestamoResponse> prestamos = prestamoClient.obtenerPrestamosConCliente();
        model.addAttribute("prestamos", prestamos);
        return "prestamos";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model, HttpSession session) {
        String token = (String) session.getAttribute("jwt");
        if (token == null) {
            return "redirect:/inicio/login?sessionExpired=true";
        }

        model.addAttribute("prestamoRequest", new PrestamoRequest());
        model.addAttribute("clientes", clienteClient.obtenerClientes()); // para seleccionar cliente
        return "prestamos-registro";
    }

    @PostMapping("/crear")
    public String crearPrestamo(@ModelAttribute PrestamoRequest prestamoRequest,
                                HttpSession session, Model model) {
        String token = (String) session.getAttribute("jwt");
        if (token == null) {
            return "redirect:/inicio/login?sessionExpired=true";
        }

        try {
            prestamoClient.crearPrestamo(prestamoRequest);
            return "redirect:/inicio/prestamos";
        } catch (Exception e) {
            model.addAttribute("error", "Error al registrar préstamo");
            model.addAttribute("clientes", clienteClient.obtenerClientes());
            return "prestamos-registro";
        }
    }

}
