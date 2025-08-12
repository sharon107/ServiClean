package org.ServiClean.servicios.implementaciones;

import org.ServiClean.modelos.Auditoria;
import org.ServiClean.repositorios.IAuditoriaRepository;
import org.ServiClean.servicios.interfaces.IAuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuditoriaService implements IAuditoriaService {

    @Autowired
    private IAuditoriaRepository auditoriaRepository;

    @Override
    public Page<Auditoria> buscarTodosPaginados(Pageable pageable) {
        return auditoriaRepository.findAll(pageable);
    }

    @Override
    public List<Auditoria> obtenerTodos() {
        return auditoriaRepository.findAll();
    }

    @Override
    public Optional<Auditoria> buscarPorId(Integer id) {
        return auditoriaRepository.findById(id);
    }

    @Override
    public Auditoria crearOEditar(Auditoria auditoria) {
        return auditoriaRepository.save(auditoria);
    }

    @Override
    public void eliminarPorId(Integer id) {
        auditoriaRepository.deleteById(id);
    }
}
