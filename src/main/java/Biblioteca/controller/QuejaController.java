/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Biblioteca.controller;
import Biblioteca.domain.Queja;
import Biblioteca.service.QuejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/queja")
public class QuejaController {

    @Autowired
    private QuejaService quejaService;

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("quejas", quejaService.listar());
        model.addAttribute("titulo", "Quejas y Sugerencias");
        return "queja/lista";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("queja", new Queja());
        model.addAttribute("titulo", "Enviar Queja o Sugerencia");
        return "queja/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Queja queja) {
        quejaService.guardar(queja);
        return "redirect:/queja/lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        quejaService.eliminar(id);
        return "redirect:/queja/lista";
    }
}