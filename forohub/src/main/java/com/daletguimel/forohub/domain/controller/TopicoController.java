package com.daletguimel.forohub.domain.controller;

import com.daletguimel.forohub.domain.topico.CrearTopicoDTO;
import com.daletguimel.forohub.domain.topico.Topico;
import com.daletguimel.forohub.domain.topico.TopicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @PostMapping
    public ResponseEntity<Topico> registrarTopico(@RequestBody @Valid CrearTopicoDTO dto){
        Topico nuevoTopico = topicoService.registrarTopico(dto);
        return ResponseEntity.ok(nuevoTopico);
    }
}
