package org.ServiClean.controladores;

import org.ServiClean.modelos.Rol;
import org.ServiClean.modelos.Usuario;
import org.ServiClean.servicios.interfaces.IRolService;
import org.ServiClean.servicios.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRolService rolService;

    @GetMapping
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Usuario> usuarios = usuarioService.buscarTodosPaginados(pageable);
        model.addAttribute("usuarios", usuarios);

        int totalPages = usuarios.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "usuario/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", rolService.obtenerTodos());
        return "usuario/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            model.addAttribute("roles", rolService.obtenerTodos());
            return "usuario/create";
        }

        if(usuario.getRoles() != null){
            usuario.setRol(usuario.getRoles().get(0));
        }

        if (usuario.getRoles() == null){
            ArrayList<Rol> Listaroles = new ArrayList<Rol>();
            Listaroles.add(usuario.getRol());

            usuario.setRoles(Listaroles);
        }


        usuarioService.crearOEditar(usuario);
        attributes.addFlashAttribute("msg", "Usuario creado correctamente");
        return "redirect:/usuarios";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model, RedirectAttributes attributes){
        Optional<Usuario> usuarioOptional = usuarioService.buscarPorId(id);
        if (usuarioOptional.isPresent()) {
            model.addAttribute("usuario", usuarioOptional.get());
            return "usuario/details";
        } else {
            attributes.addFlashAttribute("error", "El usuario no fue encontrado.");
            return "redirect:/usuarios";
        }
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model, RedirectAttributes attributes){
        Optional<Usuario> usuarioOptional = usuarioService.buscarPorId(id);
        if (usuarioOptional.isPresent()) {
            model.addAttribute("usuario", usuarioOptional.get());
            model.addAttribute("roles", rolService.obtenerTodos());
            return "usuario/edit";
        } else {
            attributes.addFlashAttribute("error", "El usuario no fue encontrado.");
            return "redirect:/usuarios";
        }
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model, RedirectAttributes attributes){
        Optional<Usuario> usuarioOptional = usuarioService.buscarPorId(id);
        if (usuarioOptional.isPresent()) {
            model.addAttribute("usuario", usuarioOptional.get());
            return "usuario/delete";
        } else {
            attributes.addFlashAttribute("error", "El usuario no fue encontrado.");
            return "redirect:/usuarios";
        }
    }

    @PostMapping("/delete")
    public String delete(Usuario usuario, RedirectAttributes attributes){
        usuarioService.eliminarPorId(usuario.getId());
        attributes.addFlashAttribute("msg", "Usuario eliminado correctamente");
        return "redirect:/usuarios";
    }
}