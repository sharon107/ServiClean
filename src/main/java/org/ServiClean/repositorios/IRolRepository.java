package org.ServiClean.repositorios;

import org.ServiClean.modelos.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // 🔹 No es obligatorio, pero es buena práctica
public interface IRolRepository extends JpaRepository<Rol, Integer> {
}
