/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Biblioteca.controller;

import Biblioteca.domain.Libro;
import Biblioteca.service.LibroService;
import Biblioteca.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("libros", libroService.listar());
        model.addAttribute("titulo", "Listado de Libros");
        return "libro/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("libro", new Libro());
        model.addAttribute("categorias", categoriaService.listar());
        model.addAttribute("titulo", "Nuevo Libro");
        return "libro/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Libro libro) {
        libroService.guardar(libro);
        return "redirect:/libro/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Libro libro = libroService.obtenerPorId(id);
        if (libro == null) {
            return "redirect:/libro/lista";
        }
        model.addAttribute("libro", libro);
        model.addAttribute("categorias", categoriaService.listar());
        model.addAttribute("titulo", "Editar Libro");
        return "libro/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        libroService.eliminar(id);
        return "redirect:/libro/lista";
    }
}