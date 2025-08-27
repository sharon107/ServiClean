package org.ServiClean.controladores;

import org.ServiClean.modelos.Equipo;
import org.ServiClean.servicios.interfaces.IEquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/equipos")
public class EquipoController {

    @Autowired
    private IEquipoService equipoService;

    @GetMapping
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Equipo> equipos = equipoService.buscarTodosPaginados(pageable);
        model.addAttribute("equipos", equipos);

        int totalPages = equipos.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "equipo/index";
    }

    @GetMapping("/create")
    public String create (Equipo equipo){
        return "equipo/create";
    }

    @PostMapping("/save")
    public String save(Equipo equipo, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute(equipo);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "equipo/create";
        }

        equipoService.crearOEditar(equipo);
        attributes.addFlashAttribute("msg", "Equipo creado correctamente");
        return "redirect:/equipos";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model){
        Equipo equipo = equipoService.buscarPorId(id).get();
        model.addAttribute("equipo", equipo);
        return "equipo/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Equipo equipo = equipoService.buscarPorId(id).get();
        model.addAttribute("equipo", equipo);
        return "equipo/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        Equipo equipo = equipoService.buscarPorId(id).get();
        model.addAttribute("equipo", equipo);
        return "equipo/delete";
    }

    @PostMapping("/delete")
    public String delete(Equipo equipo, RedirectAttributes attributes){
        equipoService.eliminarPorId(equipo.getId());
        attributes.addFlashAttribute("msg", "Equipo eliminado correctamente");
        return "redirect:/equipos";
    }

}