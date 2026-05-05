package sv.edu.udb.parcial3.config.web;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ApiErrorWraper{
    private final List<ApiError> errores = new ArrayList<>();

    public final void addApiError(final ApiError error){
        errores.add(error);
    }

    public final void addFieldError(final String titulo,
                                    final String tipo,
                                    final String fuente,
                                    final String descripcion){
        final ApiError error = ApiError.builder()
                .estatus(HttpStatus.BAD_REQUEST.value())
                .titulo(titulo)
                .tipo(tipo)
                .fuente(fuente)
                .descripcion(descripcion)
                .build();

        errores.add(error);
    }

}
