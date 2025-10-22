/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Biblioteca.controller;

import Biblioteca.domain.Categoria;
import Biblioteca.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("categorias", categoriaService.listar());
        model.addAttribute("titulo", "Listado de Categorías");
        return "categoria/lista";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("categoria", new Categoria());
        model.addAttribute("titulo", "Nueva Categoría");
        return "categoria/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Categoria categoria) {
        categoriaService.guardar(categoria);
        return "redirect:/categoria/lista";
    }

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

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        categoriaService.eliminar(id);
        return "redirect:/categoria/lista";
    }
}