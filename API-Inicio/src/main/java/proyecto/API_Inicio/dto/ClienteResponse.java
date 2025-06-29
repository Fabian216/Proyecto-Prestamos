package proyecto.API_Inicio.dto;

import lombok.Data;

@Data
public class ClienteResponse {
    private Integer id;
    private String dni;
    private String nombres;
    private String apellidos;
    private String email;
    private String celular;
}
