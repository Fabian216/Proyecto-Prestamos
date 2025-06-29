package proyecto.API_Inicio.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PrestamoResponse {
    private Integer id;
    private BigDecimal monto;
    private BigDecimal restante;
    private LocalDate fechaPrestamo;

    // Datos del cliente
    private Integer clienteId;
    private String clienteDni;
    private String clienteNombres;
    private String clienteApellidos;
}
