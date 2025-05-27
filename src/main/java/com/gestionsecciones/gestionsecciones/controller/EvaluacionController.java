package com.gestionsecciones.gestionsecciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionsecciones.gestionsecciones.model.Evaluacion;
import com.gestionsecciones.gestionsecciones.service.EvaluacionService;

@RestController
@RequestMapping("/api/evaluacion")
public class EvaluacionController {
    
    @Autowired
    private EvaluacionService evaluacionService;

    @PostMapping
    public ResponseEntity<Evaluacion> postEvaluacion(@RequestBody Evaluacion evaluacion) {
        Evaluacion buscado = evaluacionService.findxIdEvaluacion(evaluacion.getIdEvaluacion());
        if (buscado == null) {
            return new ResponseEntity<>(evaluacionService.crearEvaluacion(evaluacion), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<List<Evaluacion>> getEvaluacion() {
        List<Evaluacion> lista = evaluacionService.findAllEvaluaciones();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(lista, HttpStatus.ACCEPTED);
        }
    }

    @PutMapping("/{idEvaluacion}")
    public ResponseEntity<Evaluacion> putEvaluacion(@PathVariable Integer idEvaluacion, @RequestBody Evaluacion evaluacion) {
        Evaluacion actualizado = evaluacionService.editEvaluacion(idEvaluacion, evaluacion);
        if (actualizado != null) {
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idEvaluacion}")
    public ResponseEntity<Evaluacion> deleteEvaluacion(@PathVariable Integer idEvaluacion) {
        Evaluacion eliminado = evaluacionService.eliminarEvaluacion(idEvaluacion);
        if (eliminado != null) {
            return new ResponseEntity<>(eliminado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

    
}
