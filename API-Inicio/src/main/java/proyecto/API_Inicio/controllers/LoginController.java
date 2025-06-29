package proyecto.API_Inicio.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proyecto.API_Inicio.clients.AuthClient;
import proyecto.API_Inicio.dto.AuthRequest;
import proyecto.API_Inicio.dto.AuthResponse;

@Controller
@RequestMapping("/inicio")
public class LoginController {
    @Autowired
    private AuthClient authClient;

    @GetMapping("/login")
    public String mostrarFormularioLogin(@RequestParam(required = false) Boolean sessionExpired,
                                         Model model) {
        model.addAttribute("authRequest", new AuthRequest());

        if (Boolean.TRUE.equals(sessionExpired)) {
            model.addAttribute("expiredMessage", "Tu sesión ha expirado. Por favor, inicia sesión.");
        }

        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@ModelAttribute AuthRequest authRequest, HttpSession session, Model model) {
        try {
            AuthResponse response = authClient.login(authRequest);
            session.setAttribute("jwt", response.getToken()); // Guardar token en sesión
            return "redirect:/inicio/home"; // Redirigir al inicio
        } catch (Exception e) {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }

    @GetMapping("/home")
    public String mostrarHome(HttpSession session) {
        String token = (String) session.getAttribute("jwt");
        if (token == null) {
            return "redirect:/inicio/login?sessionExpired=true";
        }
        return "home";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Destruye la sesión
        return "redirect:/inicio/login";
    }

}
