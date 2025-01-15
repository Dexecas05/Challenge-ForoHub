package com.daletguimel.forohub.controller;

import com.daletguimel.forohub.domain.topico.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Tópicos", description = "Funcionalidades relacionadas con los tópicos")
public class TopicoController {

    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @PostMapping
    @Operation(summary = "Crear nuevo tópico", description = "Permite la creación y registro de un nuevo tópico")
    public ResponseEntity<CrearTopicoDTO> registrarTopico(
            @RequestBody @Valid CrearTopicoDTO dto,
            UriComponentsBuilder uriComponentsBuilder){
        Topico nuevoTopico = topicoService.registrarTopico(dto);
        URI uri = uriComponentsBuilder.path("/topicos/{id}")
                .buildAndExpand(nuevoTopico.getId())
                .toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    @Operation(summary = "Listar todos los tópicos", description = "Devuelve una lista con todos los tópicos registrados y no borrados")
    public ResponseEntity<Page<TopicoDTO>> listarTopicos(
            @PageableDefault(size = 10, sort = "fechaCreacion") Pageable pageable) {
        return ResponseEntity.ok(topicoService.listarTopicos(pageable));
    }

    @GetMapping("/primeros10")
    @Operation(summary = "Primeros diez tópicos", description = "Devuelve una lista con los tópicos más recientes")
    public ResponseEntity<List<TopicoDTO>> listarPrimeros10Topicos(){
        return ResponseEntity.ok(topicoService.listarPrimeros10Topicos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Bucar tópico por id", description = "Devuelve un tópico por número de id")
    public ResponseEntity<TopicoDTO> obtenerTopicoPorId(@PathVariable Long id) {
        TopicoDTO topicoDTO = topicoService.obtenerTopicoPorId(id);
        return ResponseEntity.ok(topicoDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar tópico", description = "Permite actualizar la información de un tópico determinado por id")
    public ResponseEntity<TopicoDTO> actualizarTopico(@PathVariable Long id,
                                                      @RequestBody @Valid ActualizarTopicoDTO dto) {
        TopicoDTO topicoActualizado = topicoService.actualizarTopico(id, dto);
        return ResponseEntity.ok(topicoActualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar tópico", description = "Permite la eliminación tópico determinado por id")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
