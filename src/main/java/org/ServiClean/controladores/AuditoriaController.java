package org.ServiClean.controladores;

import org.ServiClean.modelos.Auditoria;
import org.ServiClean.servicios.interfaces.IAuditoriaService;
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
@RequestMapping("/auditorias")
public class AuditoriaController {

    @Autowired
    private IAuditoriaService auditoriaService;

    @GetMapping
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Auditoria> auditorias = auditoriaService.buscarTodosPaginados(pageable);
        model.addAttribute("auditorias", auditorias);

        int totalPages = auditorias.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "auditoria/index";
    }

    @GetMapping("/create")
    public String create (Auditoria auditoria){
        return "auditoria/create";
    }

    @PostMapping("/save")
    public String save(Auditoria auditoria, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute(auditoria);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "auditoria/create";
        }

        auditoriaService.crearOEditar(auditoria);
        attributes.addFlashAttribute("msg", "Auditoria creada correctamente");
        return "redirect:/auditorias";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model){
        Auditoria auditoria = auditoriaService.buscarPorId(id).get();
        model.addAttribute("auditoria", auditoria);
        return "auditoria/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Auditoria auditoria = auditoriaService.buscarPorId(id).get();
        model.addAttribute("auditoria", auditoria);
        return "auditoria/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        Auditoria auditoria = auditoriaService.buscarPorId(id).get();
        model.addAttribute("auditoria", auditoria);
        return "auditoria/delete";
    }

    @PostMapping("/delete")
    public String delete(Auditoria auditoria, RedirectAttributes attributes){
        auditoriaService.eliminarPorId(auditoria.getId());
        attributes.addFlashAttribute("msg", "Auditoria eliminada correctamente");
        return "redirect:/auditorias";
    }

}