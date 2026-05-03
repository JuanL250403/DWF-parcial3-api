package sv.edu.udb.parcial3.controller.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String usuario;
    private String contrasenia;
}
