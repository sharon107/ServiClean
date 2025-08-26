package org.ServiClean.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String index(){
        return "home/index";
    }

    @GetMapping("/tarea/historial")
    public String redirectToHistorial() {
        return "redirect:/tarea/historial";
    }
}
