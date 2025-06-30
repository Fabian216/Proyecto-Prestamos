package proyecto.API_Inicio.controllers;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import proyecto.API_Inicio.clients.PrestamoClient;
import proyecto.API_Inicio.dto.PrestamoResponse;
import proyecto.API_Inicio.util.ReportePdfUtil;

import java.util.List;

@Controller
public class ReporteController {

    @Autowired
    private PrestamoClient prestamoClient;

    @GetMapping("/inicio/prestamos/reporte")
    public void generarReportePrestamos(HttpServletResponse response, HttpSession session) {
        String token = (String) session.getAttribute("jwt");
        if (token == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try {
            List<PrestamoResponse> prestamos = prestamoClient.obtenerPrestamosConCliente();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=prestamos.pdf");

            ReportePdfUtil.exportarPrestamosPdf(prestamos, response.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el PDF", e);
        }
    }

}
