package proyecto.API_Finanzas.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AbonoRequest {
    private String banco;
    private BigDecimal monto;
    private LocalDate fechaAbono;
    private Integer prestamoId;
}
