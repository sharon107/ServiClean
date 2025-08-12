package org.ServiClean.repositorios;

import org.ServiClean.modelos.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITareaRepository extends JpaRepository<Tarea, Integer> {
}
