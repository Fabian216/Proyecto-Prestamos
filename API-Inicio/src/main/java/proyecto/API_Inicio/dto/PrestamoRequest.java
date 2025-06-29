package proyecto.API_Inicio.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PrestamoRequest {
    private BigDecimal monto;
    private LocalDate fechaPrestamo;
    private Integer clienteId;
}
