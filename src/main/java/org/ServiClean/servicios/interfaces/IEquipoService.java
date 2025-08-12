package org.ServiClean.servicios.interfaces;

import org.ServiClean.modelos.Equipo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IEquipoService {
    Page<Equipo> buscarTodosPaginados(Pageable pageable);

    List<Equipo> obtenerTodos();

    Optional<Equipo> buscarPorId(Integer id);

    Equipo crearOEditar(Equipo equipo);

    void eliminarPorId(Integer id);
}
