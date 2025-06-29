package proyecto.API_Finanzas.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "abono")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Abono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_abono")
    private Integer id;

    private String banco;
    private BigDecimal monto;

    @Column(name = "fecha_abono")
    private LocalDate fechaAbono;

    @ManyToOne
    @JoinColumn(name = "id_prestamo")
    private Prestamo prestamo;

}
