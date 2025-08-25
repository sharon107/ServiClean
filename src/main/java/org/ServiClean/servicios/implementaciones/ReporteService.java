package org.ServiClean.servicios.implementaciones;

import org.ServiClean.modelos.Reporte;
import org.ServiClean.repositorios.IReporteRepository;
import org.ServiClean.servicios.interfaces.IReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteService implements IReporteService {

    @Autowired
    private IReporteRepository reporteRepository;

    @Override
    public Page<Reporte> buscarTodosPaginados(Pageable pageable) {

        return reporteRepository.findAll(pageable);
    }

    @Override
    public List<Reporte> obtenerTodos() {

        return reporteRepository.findAll();
    }

    @Override
    public Optional<Reporte> buscarPorId(Integer id) {

        return reporteRepository.findById(id);
    }

    @Override
    public Reporte crearOEditar(Reporte reporte) {

        return reporteRepository.save(reporte);
    }

    @Override
    public void eliminarPorId(Integer id) {

        reporteRepository.deleteById(id);
    }
}
