package org.ServiClean.controladores;

import org.ServiClean.modelos.Reporte;
import org.ServiClean.servicios.interfaces.IReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private IReporteService reporteService;

    @GetMapping
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);

        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<Reporte> reportes = reporteService.buscarTodosPaginados(pageable);
        model.addAttribute("reportes", reportes);

        int totalPages = reportes.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "reporte/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        // Simplemente crea un nuevo objeto Reporte y lo añade al modelo
        Reporte reporte = new Reporte();
        model.addAttribute("reporte", reporte);
        return "reporte/create";
    }

    @PostMapping("/save")
    public String save(Reporte reporte, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "reporte/create";
        }

        reporte.setFechaGenerado(LocalDate.now());

        reporteService.crearOEditar(reporte);
        attributes.addFlashAttribute("msg", "Reporte creado correctamente");
        return "redirect:/reportes";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model){
        Optional<Reporte> reporteOptional = reporteService.buscarPorId(id);
        if (reporteOptional.isPresent()) {
            model.addAttribute("reporte", reporteOptional.get());
            return "reporte/details";
        }
        return "redirect:/reportes";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Optional<Reporte> reporteOptional = reporteService.buscarPorId(id);
        if (reporteOptional.isPresent()) {
            model.addAttribute("reporte", reporteOptional.get());
            return "reporte/edit";
        }
        return "redirect:/reportes";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        Optional<Reporte> reporteOptional = reporteService.buscarPorId(id);
        if (reporteOptional.isPresent()) {
            model.addAttribute("reporte", reporteOptional.get());
            return "reporte/delete";
        }
        return "redirect:/reportes";
    }

    @PostMapping("/delete")
    public String delete(Reporte reporte, RedirectAttributes attributes){
        reporteService.eliminarPorId(reporte.getId());
        attributes.addFlashAttribute("msg", "Reporte eliminado correctamente");
        return "redirect:/reportes";
    }
}