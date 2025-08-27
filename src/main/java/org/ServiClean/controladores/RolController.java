package org.ServiClean.controladores;

import org.ServiClean.modelos.Rol;
import org.ServiClean.servicios.interfaces.IRolService;
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
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private IRolService rolService;

    @GetMapping
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Rol> roles = rolService.buscarTodosPaginados(pageable);
        model.addAttribute("roles", roles);

        int totalPages = roles.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "rol/index";
    }

    @GetMapping("/create")
    public String create (Rol rol){
        return "rol/create";
    }

    @PostMapping("/save")
    public String save(Rol rol, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute(rol);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "rol/create";
        }

        rolService.crearOEditar(rol);
        attributes.addFlashAttribute("msg", "Rol creado correctamente");
        return "redirect:/roles";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model){
        Rol rol = rolService.buscarPorId(id).get();
        model.addAttribute("rol", rol);
        return "rol/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Rol rol = rolService.buscarPorId(id).get();
        model.addAttribute("rol", rol);
        return "rol/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        Rol rol = rolService.buscarPorId(id).get();
        model.addAttribute("rol", rol);
        return "rol/delete";
    }

    @PostMapping("/delete")
    public String delete(Rol rol, RedirectAttributes attributes){
        rolService.eliminarPorId(rol.getId());
        attributes.addFlashAttribute("msg", "Rol eliminado correctamente");
        return "redirect:/roles";
    }




}