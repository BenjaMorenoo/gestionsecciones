package com.gestionsecciones.gestionsecciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionsecciones.gestionsecciones.model.Seccion;

@Repository
public interface SeccionRepository extends JpaRepository<Seccion, Integer> {




    /*
     * +crearSeccion()
     * +editarSeccion(nombre: String)
     * +eliminarSeccion()
     * +Listar Seccion(): Seccion
     * +asignarProfesorSeccion(profesorId: String)
     * +asignarAlumnosSeccion(listaAlumnos: List<String>)
     * 
     */

    
    Seccion save(Seccion seccion);
    Seccion findById(int idSeccion);
    Seccion deleteById(int idSeccion);
    List<Seccion> findAll();
}
