package com.custodio.denunciaservice.controller;

import com.custodio.denunciaservice.entity.Denuncia;
import com.custodio.denunciaservice.service.DenunciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/denuncias")
@CrossOrigin(origins = "*")
public class DenunciaController {

    private final DenunciaService service;

    public DenunciaController(DenunciaService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<Denuncia> registrar(@RequestBody Denuncia d) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(d));
    }

    @GetMapping("/")
    public List<Denuncia> listar(@RequestParam(defaultValue = "0") int offset,
                                 @RequestParam(defaultValue = "5") int limit) {
        return service.listar(offset, limit);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Denuncia> obtener(@PathVariable Integer id) {
        return service.obtener(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ciudadano/{dni}")
    public List<Denuncia> porCiudadano(@PathVariable String dni) {
        return service.porCiudadano(dni);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Denuncia> atender(@PathVariable Integer id) {
        return service.atender(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}