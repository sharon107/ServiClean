package org.ServiClean.repositorios;

import org.ServiClean.modelos.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ITareaRepository extends JpaRepository<Tarea, Integer> {
    List<Tarea> findAllByOrderByFechaAsignacionDesc();
}
