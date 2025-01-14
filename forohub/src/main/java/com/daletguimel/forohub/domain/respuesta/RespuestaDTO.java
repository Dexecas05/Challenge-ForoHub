package com.daletguimel.forohub.domain.respuesta;

import java.time.LocalDateTime;

public record RespuestaDTO(
        Long id,
        String mensaje,
        LocalDateTime fechaCreacion,
        Long topicoId,
        Long autorId
) {
}
