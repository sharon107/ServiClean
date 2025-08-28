package org.ServiClean.servicios.implementaciones;

import org.ServiClean.modelos.Tarea;
import org.ServiClean.repositorios.ITareaRepository;
import org.ServiClean.servicios.interfaces.ITareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService implements ITareaService {

    @Autowired
    private ITareaRepository tareaRepository;

    @Override
    public Page<Tarea> buscarTodosPaginados(Pageable pageable) {
        return tareaRepository.findAll(pageable);
    }

    @Override
    public List<Tarea> obtenerTodos() {
        return tareaRepository.findAll();
    }

    @Override
    public Optional<Tarea> buscarPorId(Integer id) {
        return tareaRepository.findById(id);
    }

    @Override
    public Tarea crearOEditar(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    @Override
    public void eliminarPorId(Integer id) {
        tareaRepository.deleteById(id);
    }

    public void asignarTareaCorreo(Integer tareaId, String correo) {
        Tarea tarea = tareaRepository.findById(tareaId).orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
        tarea.setCorreoAsignado(correo);
        tareaRepository.save(tarea);
    }

}
