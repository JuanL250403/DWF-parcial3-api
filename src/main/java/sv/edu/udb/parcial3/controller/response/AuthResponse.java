package sv.edu.udb.parcial3.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String authToken;
    private String refreshToken;
}
