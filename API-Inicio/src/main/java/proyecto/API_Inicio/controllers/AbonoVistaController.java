package proyecto.API_Inicio.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proyecto.API_Inicio.clients.AbonoClient;
import proyecto.API_Inicio.dto.AbonoRequest;
import proyecto.API_Inicio.dto.AbonoResponse;

import java.util.List;

@Controller
@RequestMapping("/inicio/abonos")
public class AbonoVistaController {
    @Autowired
    private AbonoClient abonoClient;

    /*@GetMapping("/{prestamoId}")
    public String listarAbonosPorPrestamo(@PathVariable Integer prestamoId, Model model, HttpSession session) {
        String token = (String) session.getAttribute("jwt");
        if (token == null) {
            return "redirect:/inicio/login?sessionExpired=true";
        }

        List<AbonoResponse> abonos = abonoClient.obtenerAbonosPorPrestamo(prestamoId);
        model.addAttribute("abonos", abonos);
        return "abonos"; //vista abonos
    }*/
    @GetMapping("/{prestamoId}")
    public String listarAbonosPorPrestamo(@PathVariable Integer prestamoId, Model model, HttpSession session) {
        String token = (String) session.getAttribute("jwt");
        if (token == null) {
            return "redirect:/inicio/login?sessionExpired=true";
        }

        List<AbonoResponse> abonos = abonoClient.obtenerAbonosPorPrestamo(prestamoId);
        model.addAttribute("abonos", abonos);
        model.addAttribute("abonoRequest", new AbonoRequest()); // formulario
        model.addAttribute("prestamoId", prestamoId);
        return "abonos";
    }


    @PostMapping("/{prestamoId}/registrar")
    public String registrarAbono(@PathVariable Integer prestamoId,
                                 @ModelAttribute AbonoRequest abonoRequest,
                                 HttpSession session, Model model) {
        String token = (String) session.getAttribute("jwt");
        if (token == null) {
            return "redirect:/inicio/login?sessionExpired=true";
        }

        abonoRequest.setPrestamoId(prestamoId); // importante
        try {
            abonoClient.registrarAbono(abonoRequest);
            return "redirect:/inicio/abonos/" + prestamoId;
        } catch (Exception e) {
            model.addAttribute("error", "Error al registrar abono");
            return "abonos";
        }
    }


}
