package proyecto.API_Inicio.dto;

import lombok.Data;

@Data
public class ClienteRequest {
    private String dni;
    private String nombres;
    private String apellidos;
    private String email;
    private String celular;
}
