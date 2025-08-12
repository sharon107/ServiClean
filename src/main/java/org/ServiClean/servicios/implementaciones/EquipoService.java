package org.ServiClean.servicios.implementaciones;

import org.ServiClean.modelos.Equipo;
import org.ServiClean.repositorios.IEquipoRepository;
import org.ServiClean.servicios.interfaces.IEquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoService implements IEquipoService {

    @Autowired
    private IEquipoRepository equipoRepository;

    @Override
    public Page<Equipo> buscarTodosPaginados(Pageable pageable) {
        return equipoRepository.findAll(pageable);
    }

    @Override
    public List<Equipo> obtenerTodos() {
        return equipoRepository.findAll();
    }

    @Override
    public Optional<Equipo> buscarPorId(Integer id) {
        return equipoRepository.findById(id);
    }

    @Override
    public Equipo crearOEditar(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    @Override
    public void eliminarPorId(Integer id) {
        equipoRepository.deleteById(id);
    }
}
