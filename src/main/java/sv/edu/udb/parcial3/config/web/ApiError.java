package sv.edu.udb.parcial3.config.web;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiError {
    private Integer estatus;

    private String titulo;

    private String tipo;

    @Builder.Default
    private String fuente = "base";

    private String descripcion;
}
