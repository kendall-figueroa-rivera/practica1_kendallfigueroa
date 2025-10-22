/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Biblioteca.controller;

import Biblioteca.domain.Categoria;
import Biblioteca.domain.Libro;
import Biblioteca.service.CategoriaService;
import Biblioteca.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private LibroService libroService; // Inyectamos LibroService para el libro destacado

    // Método para la página de inicio
@GetMapping("/")
public String inicio(Model model) {
    // Tomamos el primer libro como destacado, si no hay ninguno, creamos uno vacío
    Libro libroDestacado = libroService.listar().stream().findFirst().orElse(new Libro());
    model.addAttribute("libro", libroDestacado);

    
    // Pasamos la lista de categorías al modelo
    model.addAttribute("categorias", categoriaService.listar());

    return "index"; // Renderiza index.html
}

    // Listado de categorías
    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("categorias", categoriaService.listar());
        model.addAttribute("titulo", "Listado de Categorías");
        return "categoria/lista";
    }

    // Formulario nueva categoría
    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("categoria", new Categoria());
        model.addAttribute("titulo", "Nueva Categoría");
        return "categoria/form";
    }

    // Guardar categoría
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Categoria categoria) {
        categoriaService.guardar(categoria);
        return "redirect:/categoria/lista";
    }

    // Editar categoría
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Categoria categoria = categoriaService.obtenerPorId(id);
        if (categoria == null) {
            return "redirect:/categoria/lista";
        }
        model.addAttribute("categoria", categoria);
        model.addAttribute("titulo", "Editar Categoría");
        return "categoria/form";
    }

    // Eliminar categoría
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        categoriaService.eliminar(id);
        return "redirect:/categoria/lista";
    }
}