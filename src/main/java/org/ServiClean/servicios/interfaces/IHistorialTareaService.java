package org.ServiClean.servicios.interfaces;

import org.ServiClean.modelos.HistorialTarea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IHistorialTareaService {
    Page<HistorialTarea> buscarTodosPaginados(Pageable pageable);

    List<HistorialTarea> obtenerTodos();

    Optional<HistorialTarea> buscarPorId(Integer id);

    HistorialTarea crearOEditar(HistorialTarea historialTarea);

    void eliminarPorId(Integer id);
}
