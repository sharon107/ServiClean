package org.ServiClean.repositorios;

import org.ServiClean.modelos.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuditoriaRepository extends JpaRepository<Auditoria, Integer> {
}
