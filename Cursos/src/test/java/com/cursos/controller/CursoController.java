package com.cursos.controller;

import com.cursos.Cursos.Model.Curso;
import com.cursos.Cursos.Services.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @Operation(summary = "Obtener todos los cursos")
    @GetMapping
    public CollectionModel<EntityModel<Curso>> getAllCursos() {
        List<EntityModel<Curso>> cursos = cursoService.getAllCursos().stream()
            .map(curso -> EntityModel.of(curso,
                linkTo(methodOn(CursoController.class).getCursoById(curso.getIdCurso())).withSelfRel(),
                linkTo(methodOn(CursoController.class).getAllCursos()).withRel("cursos")))
            .collect(Collectors.toList());
        return CollectionModel.of(cursos, linkTo(methodOn(CursoController.class).getAllCursos()).withSelfRel());
    }

    @Operation(summary = "Obtener un curso por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Curso>> getCursoById(@PathVariable Long id) {
        Curso curso = cursoService.getCursoById(id);
        if (curso != null) {
            EntityModel<Curso> resource = EntityModel.of(curso,
                linkTo(methodOn(CursoController.class).getCursoById(id)).withSelfRel(),
                linkTo(methodOn(CursoController.class).getAllCursos()).withRel("cursos"));
            return ResponseEntity.ok(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Crear un nuevo curso")
    @PostMapping
    public ResponseEntity<EntityModel<Curso>> createCurso(@RequestBody Curso curso) {
        Curso saved = cursoService.saveCurso(curso);
        EntityModel<Curso> resource = EntityModel.of(saved,
            linkTo(methodOn(CursoController.class).getCursoById(saved.getIdCurso())).withSelfRel(),
            linkTo(methodOn(CursoController.class).getAllCursos()).withRel("cursos"));
        return ResponseEntity.created(linkTo(methodOn(CursoController.class).getCursoById(saved.getIdCurso())).toUri()).body(resource);
    }

    @Operation(summary = "Actualizar un curso")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Curso>> updateCurso(@PathVariable Long id, @RequestBody Curso curso) {
        Curso updated = cursoService.updateCurso(id, curso);
        if (updated != null) {
            EntityModel<Curso> resource = EntityModel.of(updated,
                linkTo(methodOn(CursoController.class).getCursoById(updated.getIdCurso())).withSelfRel(),
                linkTo(methodOn(CursoController.class).getAllCursos()).withRel("cursos"));
            return ResponseEntity.ok(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un curso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        cursoService.deleteCurso(id);
        return ResponseEntity.noContent().build();
    }
}


