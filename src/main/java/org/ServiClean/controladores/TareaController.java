package org.ServiClean.controladores;

import org.ServiClean.modelos.Rol;
import org.ServiClean.modelos.Tarea;
import org.ServiClean.servicios.interfaces.ITareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    private ITareaService tareaService;

    @GetMapping
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(30);

        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<Tarea> tareas = tareaService.buscarTodosPaginados(pageable);
        model.addAttribute("tareas", tareas);

        int totalPages = tareas.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "tarea/index";
    }

    @GetMapping("/create")
    public String create(Tarea tarea) {

        return "tarea/create";
    }

    @PostMapping("/save")
    public String save(Tarea tarea, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute(tarea);
            attributes.addFlashAttribute("error","Error no se puede guadar debido aun error");
            return "tarea/create";
        }
        tareaService.crearOEditar(tarea);
        attributes.addFlashAttribute("msg", "Tarea creada orrectamente");
        return "redirect:/tareas";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model){
        Tarea tarea = tareaService.buscarPorId(id).get();
        model.addAttribute("tarea", tarea);
        return "tarea/details";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Tarea tarea = tareaService.buscarPorId(id).get();
        model.addAttribute("tarea", tarea);
        return "tarea/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        Tarea tarea = tareaService.buscarPorId(id).get();
        model.addAttribute("tarea", tarea);
        return "tarea/delete";
    }

    @PostMapping("/delete")
    public String delete(Tarea tarea, RedirectAttributes attributes){
        tareaService.eliminarPorId(tarea.getId());
        attributes.addFlashAttribute("msg", "Tarea eliminada correctamente");
        return "redirect:/tareas";
    }

}
