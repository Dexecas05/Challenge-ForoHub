package com.daletguimel.forohub.controller;

import com.daletguimel.forohub.domain.topico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @PostMapping
    public ResponseEntity<CrearTopicoDTO> registrarTopico(@RequestBody @Valid CrearTopicoDTO dto){
        Topico nuevoTopico = topicoService.registrarTopico(dto);
        return ResponseEntity.ok(new CrearTopicoDTO(nuevoTopico.getTitulo(), nuevoTopico.getMensaje(),
                nuevoTopico.getAutor().getId(), nuevoTopico.getCurso()));
    }

    @GetMapping
    public ResponseEntity<Page<TopicoDTO>> listarTopicos(
            @PageableDefault(size = 10, sort = "fechaCreacion") Pageable pageable) {
        return ResponseEntity.ok(topicoService.listarTopicos(pageable));
    }

    @GetMapping("/primeros10")
    public ResponseEntity<List<TopicoDTO>> listarPrimeros10Topicos(){
        return ResponseEntity.ok(topicoService.listarPrimeros10Topicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDTO> obtenerTopicoPorId(@PathVariable Long id) {
        TopicoDTO topicoDTO = topicoService.obtenerTopicoPorId(id);
        return ResponseEntity.ok(topicoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoDTO> actualizarTopico(@PathVariable Long id,
                                                      @RequestBody @Valid ActualizarTopicoDTO dto) {
        TopicoDTO topicoActualizado = topicoService.actualizarTopico(id, dto);
        return ResponseEntity.ok(topicoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
