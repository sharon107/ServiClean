package org.ServiClean.servicios.interfaces;

import org.ServiClean.modelos.Reporte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IReporteService {
    Page<Reporte> buscarTodosPaginados(Pageable pageable);

    List<Reporte> obtenerTodos();

    Optional<Reporte> buscarPorId(Integer id);

    Reporte crearOEditar(Reporte reporte);

    void eliminarPorId(Integer id);
}
