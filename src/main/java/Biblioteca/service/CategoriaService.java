/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Biblioteca.service;

import Biblioteca.domain.Categoria;
import Biblioteca.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import jakarta.annotation.PostConstruct;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void eliminar(Long id) {
        categoriaRepository.deleteById(id);
    }

    public Categoria obtenerPorId(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @PostConstruct
    public void inicializarDatos() {
        if (categoriaRepository.count() == 0) {
            categoriaRepository.save(new Categoria("Ficción", "Novelas y relatos de ficción."));
            categoriaRepository.save(new Categoria("Ciencia", "Libros de divulgación científica."));
            categoriaRepository.save(new Categoria("Tecnología", "Libros sobre informática y avances tecnológicos."));
        }
    }
}