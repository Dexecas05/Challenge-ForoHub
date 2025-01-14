package com.daletguimel.forohub.domain.topico;

import com.daletguimel.forohub.domain.usuario.Usuario;
import com.daletguimel.forohub.domain.usuario.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    //Listar Tópicos
    public Page<TopicoDTO> listarTopicos(Pageable pageable){
        return topicoRepository.findAll(pageable)
                .map(this::convertirTopicoADTO);
    }


    //Listar primeros 10 tópicos según fecha de creación
    public List<TopicoDTO> listarPrimeros10Topicos(){
        return topicoRepository.findAll(Sort.by(Sort.Direction.ASC, "fechaCreacion")).stream()
                .limit(10)
                .map(this::convertirTopicoADTO)
                .collect(Collectors.toList());
    }

    //Obtener detalles de tópico por id
    public TopicoDTO obtenerTopicoPorId(Long id){
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado."));
        return convertirTopicoADTO(topico);
    }

    //Actualizar tópico por id
    public TopicoDTO actualizarTopico(Long id, ActualizarTopicoDTO dto){
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()){
            Topico topico = topicoOptional.get();

            Usuario autor = usuarioRepository.findById(dto.autorId())
                    .orElseThrow(() -> new IllegalArgumentException("Autor no encontrado."));

            topico.setTitulo(dto.titulo());
            topico.setMensaje(dto.mensaje());
            topico.setAutor(autor);
            topico.setCurso(dto.curso());

            topicoRepository.save(topico);
            return convertirTopicoADTO(topico);
        } else {
            throw new IllegalArgumentException("Tópico no encontrado.");
        }
    }

    //Eliminar tópico por id
    public void eliminarTopico(Long id){
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isPresent()){
            topicoRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Tópico no encontrado.");
        }
    }

    private TopicoDTO convertirTopicoADTO(Topico topico) {
        return new TopicoDTO(
                topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                topico.getStatus(), topico.getAutor().getId(), topico.getCurso()
        );
    }
}
