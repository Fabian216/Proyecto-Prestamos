package proyecto.API_Inicio.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AbonoResponse {
    private Integer id;
    private String banco;
    private BigDecimal monto;
    private LocalDate fechaAbono;
}
