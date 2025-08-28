package org.ServiClean.servicios.interfaces;

import org.ServiClean.modelos.Rol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IRolService {

    Page<Rol> buscarTodosPaginados(Pageable pageable);

    List<Rol> obtenerTodos();

    Optional<Rol> buscarPorId(Integer id);

    Rol crearOEditar(Rol rol);

    void eliminarPorId(Integer id); // 🔹 Cambié "rol" por "id" para que sea más claro
}
