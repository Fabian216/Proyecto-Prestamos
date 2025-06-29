package proyecto.API_Inicio.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import proyecto.API_Inicio.clients.PrestamoClient;
import proyecto.API_Inicio.dto.PrestamoResponse;

import java.util.List;

@Controller
public class PrestamoVistaController {
    @Autowired
    private PrestamoClient prestamoClient;

    @GetMapping("/inicio/prestamos")
    public String listarPrestamos(Model model, HttpSession session) {
        String token = (String) session.getAttribute("jwt");
        if (token == null) {
            return "redirect:/inicio/login?sessionExpired=true";
        }

        List<PrestamoResponse> prestamos = prestamoClient.obtenerPrestamosConCliente();
        model.addAttribute("prestamos", prestamos);
        return "prestamos"; //vista prestamos
    }
}
