/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Biblioteca.service;
import Biblioteca.domain.Queja;
import Biblioteca.repository.QuejaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuejaService {

    @Autowired
    private QuejaRepository quejaRepository;

    public List<Queja> listar() {
        return quejaRepository.findAll();
    }

    public Queja guardar(Queja queja) {
        return quejaRepository.save(queja);
    }

    public void eliminar(Long id) {
        quejaRepository.deleteById(id);
    }

    public Queja obtenerPorId(Long id) {
        return quejaRepository.findById(id).orElse(null);
    }
}