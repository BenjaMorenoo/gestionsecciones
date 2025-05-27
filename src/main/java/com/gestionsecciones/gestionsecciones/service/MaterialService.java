package com.gestionsecciones.gestionsecciones.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionsecciones.gestionsecciones.model.Material;
import com.gestionsecciones.gestionsecciones.repository.MaterialRepository;

@Service
public class MaterialService {
    
    /*
     * +crearMaterial()
     * + editarMaterial()
     * + modificarEstado()
     * +ListarMaterial(): Material
     */

    @Autowired
    private MaterialRepository materialRepository;

    public Material crearMaterial(Material material) {
        return materialRepository.save(material);
    }

    public Material findxIdMaterial(int idMaterial) {
        return materialRepository.findById(idMaterial);
    }

    public List<Material> findAllMateriales() {
        return materialRepository.findAll();
    }

    public Material editMaterial(Integer idMaterial, Material material) {
        Optional<Material> materialExistente = materialRepository.findById(idMaterial);
        if (materialExistente.isPresent()) {
            Material materialActualizado = materialExistente.get();
            materialActualizado.setTitulo(material.getTitulo());
            materialActualizado.setContenido(material.getContenido());
            materialActualizado.setEstadoMaterial(material.getEstadoMaterial());

            return materialRepository.save(materialActualizado);
        }
        return null;
    }

    public Material eliminarMaterial(int idMaterial) {
        Material material = materialRepository.findById(idMaterial);
        if (material != null) {
            materialRepository.deleteById(idMaterial);
            return material;
        }
        return null;
    }

}
