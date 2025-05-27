package com.gestionsecciones.gestionsecciones.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gestionsecciones.gestionsecciones.model.CursoDTO;
import com.gestionsecciones.gestionsecciones.model.Seccion;
import com.gestionsecciones.gestionsecciones.model.UsuarioDTO;
import com.gestionsecciones.gestionsecciones.repository.SeccionRepository;

@Service
public class SeccionService {
    
    /*
     * +crearSeccion()
     * +editarSeccion(nombre: String)
     * +eliminarSeccion()
     * +Listar Seccion(): Seccion
     * +asignarProfesorSeccion(profesorId: String)
     * +asignarAlumnosSeccion(listaAlumnos: List<String>)
     * 
     */

    @Autowired
    private SeccionRepository seccionRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Seccion crearSeccion(Seccion seccion) {
        String url = "http://localhost:8082/api/curso/" + seccion.getIdCurso();
        CursoDTO curso = restTemplate.getForObject(url, CursoDTO.class);
        if (curso != null) {
            //seccion.setIdCurso(curso.getIdCurso());
            seccion.setNombreCurso(curso.getNombre());
        }
        

        return seccionRepository.save(seccion);
    }



    public Seccion findxIdSeccion(int idSeccion) {
        return seccionRepository.findById(idSeccion);
    }
    /* 
    public List<Seccion> findAllSecciones() {
        return seccionRepository.findAll();
    }*/

    public List<Seccion> findAllSecciones() {
        List<Seccion> secciones = seccionRepository.findAll();

        // Iterar sobre cada sección
        for (Seccion seccion : secciones) {
            List<String> alumnos = seccion.getAlumnos();
            
            // Si hay alumnos, recuperamos sus nombres desde el servicio de usuarios
            List<String> nombresAlumnos = new ArrayList<>();
            for (String alumnoId : alumnos) {
                String url = "http://localhost:8081/api/usuario/" + alumnoId;  // URL para obtener información del alumno
                UsuarioDTO alumno = restTemplate.getForObject(url, UsuarioDTO.class);
                
                if (alumno != null) {
                    nombresAlumnos.add(alumno.getNombre() + " " + alumno.getApellido());  // Concatenamos nombre y apellido
                }
            }
            
            // Reemplazamos la lista de IDs con la lista de nombres de alumnos
            seccion.setAlumnos(nombresAlumnos);
        }
        
        return secciones;
    }


    public Seccion editSeccion(Integer idSeccion, Seccion seccion) {
        Optional<Seccion> seccionExistente = seccionRepository.findById(idSeccion);
        if (seccionExistente.isPresent()) {
            Seccion seccionActualizado = seccionExistente.get();
            seccionActualizado.setNombreSeccion(seccion.getNombreSeccion());


            return seccionRepository.save(seccionActualizado);
        }
        return null;
    }

    public Seccion eliminarSeccion(int idSeccion) {
        Seccion seccion = seccionRepository.findById(idSeccion);
        if (seccion != null) {
            seccionRepository.deleteById(idSeccion);
            return seccion;
        }
        return null;
    }

    // +Seccion asignarProfesorSeccion(Integer id, String idUsuario)
   /*  public Seccion asignarProfesorSeccion(Integer idSeccion, String profesorId, Seccion seccion) {
        String url = "http://localhost:8081/api/usuario/" + seccion.getIdProfesor();
        UsuarioDTO profesor = restTemplate.getForObject(url, UsuarioDTO.class);
        if (profesor != null) {
            seccion.setIdProfesor(profesor.getIdUsuario());
            seccion.setProfesorAsignado(profesor.getNombre() + " " + profesor.getApellido());
        }
        Seccion sec = findxIdSeccion(idSeccion);
        if (sec != null) {
            sec.setProfesorAsignado(profesorId);
            return seccionRepository.save(sec);
        }
        return null;
    }*/

    // Método para asignar profesor a una sección
    public Seccion asignarProfesorSeccion(Integer idSeccion, String profesorId) {
        
        // Verificar si el profesor existe a través del ID proporcionado
        String url = "http://localhost:8081/api/usuario/" + profesorId; // Uso del profesorId desde el parámetro
        UsuarioDTO profesor = restTemplate.getForObject(url, UsuarioDTO.class);

        if (profesor != null) {
            Seccion seccion = findxIdSeccion(idSeccion);
            // Asignar el nombre completo del profesor a la sección
            seccion.setProfesorAsignado(profesor.getNombre() + " " + profesor.getApellido());
            seccion.setCorreoProfesorAsignado(profesor.getEmail());
            
            // Buscar la sección por ID
            
            if (seccion != null) {
                // Asignar el ID del profesor al DTO de la sección (mantener el idProfesor)
                seccion.setIdProfesor(profesor.getIdUsuario()); 
                // Guardar la sección actualizada en el repositorio
                return seccionRepository.save(seccion);
            }
        }
        // Si no se encontró al profesor o la sección, retornar null
        return null;
    }

    // +Seccion asignarAlumnosSeccion(Integer id, List<String> listaAlumnos)
    public Seccion asignarAlumnosSeccion(Integer idSeccion, List<String> nuevosAlumnos) {
        // Buscar la sección por ID
        Seccion sec = findxIdSeccion(idSeccion);
        if (sec != null) {
            List<String> actuales = sec.getAlumnos();
            // Si la lista aún no está inicializada (null), la creamos
            if (actuales == null) {
                actuales = new ArrayList<>();
                sec.setAlumnos(actuales);
            }
            // Añadimos solo los alumnos que no estén ya en la lista
            for (String alumnoId : nuevosAlumnos) {
                // Verificar si el alumno existe en el sistema
                String url = "http://localhost:8081/api/usuario/" + alumnoId;
                UsuarioDTO alumno = restTemplate.getForObject(url, UsuarioDTO.class);

                if (alumno != null && !actuales.contains(alumnoId)) {
                    actuales.add(alumnoId);  // Solo agregamos el ID, no el nombre
                } else if (alumno == null) {
                    throw new IllegalArgumentException("Alumno con ID " + alumnoId + " no existe.");
                }
            }
            // Guardar la sección con los IDs de los alumnos actualizados
            return seccionRepository.save(sec);
        }
        // Si no se encuentra la sección, retornar null
        return null;
    }

}
