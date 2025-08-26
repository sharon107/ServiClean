package org.ServiClean.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/historial-tareas")
public class HistorialTareaController {

    @GetMapping
    public String index(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "fecha") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            Model model) {

        // Crear datos de ejemplo para el historial
        List<HistorialTarea> historialList = new ArrayList<>();
        historialList.add(new HistorialTarea("Limpieza de oficina principal", LocalDateTime.now().minusDays(1), "Completada"));
        historialList.add(new HistorialTarea("Mantenimiento de baños", LocalDateTime.now().minusDays(2), "Completada"));
        historialList.add(new HistorialTarea("Limpieza de ventanas", LocalDateTime.now().minusDays(3), "Completada"));
        historialList.add(new HistorialTarea("Aspirado de alfombras", LocalDateTime.now().minusDays(4), "Completada"));
        historialList.add(new HistorialTarea("Limpieza de cocina", LocalDateTime.now().minusDays(5), "Completada"));

        // Simular paginación
        int start = page * size;
        int end = Math.min(start + size, historialList.size());
        List<HistorialTarea> pageContent = historialList.subList(start, end);

        model.addAttribute("historial", pageContent);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", (int) Math.ceil((double) historialList.size() / size));
        model.addAttribute("totalElements", historialList.size());
        model.addAttribute("size", size);

        return "historial_tarea/index";
    }

    // Clase interna para representar el historial de tareas
    public static class HistorialTarea {
        private String titulo;
        private LocalDateTime fecha;
        private String estado;

        public HistorialTarea(String titulo, LocalDateTime fecha, String estado) {
            this.titulo = titulo;
            this.fecha = fecha;
            this.estado = estado;
        }

        // Getters
        public String getTitulo() { return titulo; }
        public LocalDateTime getFecha() { return fecha; }
        public String getEstado() { return estado; }

        // Setters
        public void setTitulo(String titulo) { this.titulo = titulo; }
        public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
        public void setEstado(String estado) { this.estado = estado; }
    }

}

