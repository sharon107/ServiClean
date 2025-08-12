package org.ServiClean.servicios.implementaciones;

import org.ServiClean.modelos.HistorialTarea;
import org.ServiClean.repositorios.IHistorialTareaRepository;
import org.ServiClean.servicios.interfaces.IHistorialTareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistorialTareaService implements IHistorialTareaService {

    @Autowired
    private IHistorialTareaRepository historialTareaRepository;

    @Override
    public Page<HistorialTarea> buscarTodosPaginados(Pageable pageable) {
        return historialTareaRepository.findAll(pageable);
    }

    @Override
    public List<HistorialTarea> obtenerTodos() {
        return historialTareaRepository.findAll();
    }

    @Override
    public Optional<HistorialTarea> buscarPorId(Integer id) {
        return historialTareaRepository.findById(id);
    }

    @Override
    public HistorialTarea crearOEditar(HistorialTarea historialTarea) {
        return historialTareaRepository.save(historialTarea);
    }

    @Override
    public void eliminarPorId(Integer id) {
        historialTareaRepository.deleteById(id);
    }
}
