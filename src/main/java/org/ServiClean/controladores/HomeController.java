package org.ServiClean.controladores;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

<<<<<<< HEAD
    @GetMapping("/tarea/historial")
    public String redirectToHistorial() {
        return "redirect:/tarea/historial";
=======
    @GetMapping("/login")
    public String mostrarLogin(){
        return "home/formLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null);
        return "redirect:/";
>>>>>>> fff66f3f25479449adb236aab9d9eca2f872b195
    }
}
