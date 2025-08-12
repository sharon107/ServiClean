package org.ServiClean.servicios.interfaces;

import org.ServiClean.modelos.Auditoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IAuditoriaService {
    Page<Auditoria> buscarTodosPaginados(Pageable pageable);

    List<Auditoria> obtenerTodos();

    Optional<Auditoria> buscarPorId(Integer id);

    Auditoria crearOEditar(Auditoria auditoria);

    void eliminarPorId(Integer id);
}
