package sv.edu.udb.parcial3.controller.request;

import lombok.Data;

@Data
public class AuthRefreshRequest {
    private String refreshToken;
}
