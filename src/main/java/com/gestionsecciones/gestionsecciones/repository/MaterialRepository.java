package com.gestionsecciones.gestionsecciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionsecciones.gestionsecciones.model.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {

    /*
     * +crearMaterial()
     * + editarMaterial()
     * + modificarEstado()
     * +ListarMaterial(): Material
     */

    Material save(Material material);
    Material findById(int idMaterial);
    Material deleteById(int idMaterial);
    List<Material> findAll();

}
