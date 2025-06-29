package proyecto.API_Finanzas.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PrestamoResponse {
    private Integer id;
    private BigDecimal monto;
    private LocalDate fechaPrestamo;
    private Integer clienteId;
    private String clienteDni;
    private String clienteNombres;
    private String clienteApellidos;
}
