package sv.edu.udb.parcial3.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthRefreshResponse {
    private String authToken;
}
