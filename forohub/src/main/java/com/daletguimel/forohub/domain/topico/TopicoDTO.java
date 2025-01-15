package com.daletguimel.forohub.domain.topico;

import java.time.LocalDateTime;

public record TopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String status,
        Long autorId,
        String curso
) {
}
