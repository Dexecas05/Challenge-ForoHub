package com.daletguimel.forohub.domain.topico;

import com.daletguimel.forohub.domain.usuario.Usuario;
import com.daletguimel.forohub.domain.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;

    public TopicoService(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    //Nuevo Tópico
    public Topico registrarTopico(CrearTopicoDTO dto) {
        Optional<Topico> topicoExistente = topicoRepository.findByTituloAndMensaje(
                dto.titulo(), dto.mensaje());
        if (topicoExistente.isPresent()){
            throw new IllegalArgumentException("Ya existe un tópico con el mismo título y mensaje.");
        }
        Usuario autor = usuarioRepository.findById(dto.autorId())
                .orElseThrow(() -> new IllegalArgumentException("Autor no encontrado."));

        Topico topico = new Topico(dto.titulo(), dto.mensaje(), LocalDateTime.now(),
                "abierto", autor, dto.curso());
        return topicoRepository.save(topico);
    }
}
