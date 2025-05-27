package com.gestionsecciones.gestionsecciones.controller;

import com.gestionsecciones.gestionsecciones.model.Nota;
import com.gestionsecciones.gestionsecciones.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @PostMapping
    public Nota guardarNota(@RequestBody Nota nota) {
        return notaService.guardarNota(nota);
    }

    @GetMapping("/alumno/{alumnoId}")
    public List<Nota> obtenerNotasPorAlumno(@PathVariable int alumnoId) {
        return notaService.obtenerNotasPorAlumno(alumnoId);
    }

    @GetMapping("/evaluacion/{evaluacionId}")
    public List<Nota> obtenerNotasPorEvaluacion(@PathVariable int evaluacionId) {
        return notaService.obtenerNotasPorEvaluacion(evaluacionId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> editarNota(@PathVariable int id, @RequestBody Nota nota) {
        return notaService.actualizarNota(id, nota)
                .map(notaAct -> ResponseEntity.ok(notaAct))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNota(@PathVariable int id) {
        boolean borrada = notaService.eliminarNota(id);
        if (borrada) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
}
