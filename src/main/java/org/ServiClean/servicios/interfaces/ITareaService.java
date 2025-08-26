package org.ServiClean.servicios.interfaces;

import org.ServiClean.modelos.Tarea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ITareaService {
    Page<Tarea> buscarTodosPaginados(Pageable pageable);

    List<Tarea> obtenerTodos();

    Optional<Tarea> buscarPorId(Integer id);

    Tarea crearOEditar(Tarea tarea);

    void eliminarPorId(Integer id);

    List<Tarea> obtenerHistorial();
}
