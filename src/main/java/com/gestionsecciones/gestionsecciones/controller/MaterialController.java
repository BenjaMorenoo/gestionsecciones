package com.gestionsecciones.gestionsecciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gestionsecciones.gestionsecciones.model.Material;
import com.gestionsecciones.gestionsecciones.service.MaterialService;

@RestController
@RequestMapping("/api/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @PostMapping
    public ResponseEntity<Material> postMaterial(@RequestBody Material material) {
        Material existente = materialService.findxIdMaterial(material.getIdMaterial());
        if (existente == null) {
            Material creado = materialService.crearMaterial(material);

            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(creado);

        } else {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Material>> getMaterial() {
        List<Material> lista = materialService.findAllMateriales();
        if (lista.isEmpty()) {

            return ResponseEntity
                    .noContent()
                    .build();
        } else {

            return ResponseEntity
                    .ok(lista);
        }
    }

    @PutMapping("/{idMaterial}")
    public ResponseEntity<Material> putMaterial(@PathVariable Integer idMaterial,
            @RequestBody Material material) {
        Material actualizado = materialService.editMaterial(idMaterial, material);
        if (actualizado != null) {

            return ResponseEntity
                    .accepted()
                    .body(actualizado);
        } else {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @DeleteMapping("/{idMaterial}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Integer idMaterial) {
        Material eliminado = materialService.eliminarMaterial(idMaterial);
        if (eliminado != null) {
            return ResponseEntity
                    .noContent()
                    .build();
        } else {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}
