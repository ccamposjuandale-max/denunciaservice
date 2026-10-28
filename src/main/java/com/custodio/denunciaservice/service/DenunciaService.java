package com.custodio.denunciaservice.service;

import com.custodio.denunciaservice.entity.Denuncia;
import com.custodio.denunciaservice.repository.DenunciaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DenunciaService {

    private final DenunciaRepository repository;

    public DenunciaService(DenunciaRepository repository) {
        this.repository = repository;
    }

    public Denuncia registrar(Denuncia d) {
        d.setId(null);
        d.setEstado(1);
        if (d.getFecha() == null) {
            d.setFecha(LocalDateTime.now());
        }
        return repository.save(d);
    }

    public List<Denuncia> listar(int offset, int limit) {
        // offset = posición de inicio, limit = cantidad
        int page = offset / limit;
        return repository.findAll(PageRequest.of(page, limit, Sort.by("id").ascending()))
                .getContent();
    }

    public Optional<Denuncia> obtener(Integer id) {
        return repository.findById(id);
    }

    public List<Denuncia> porCiudadano(String dni) {
        return repository.findByDniOrderByFechaDesc(dni);
    }

    public Optional<Denuncia> atender(Integer id) {
        return repository.findById(id).map(d -> {
            d.setEstado(0);
            return repository.save(d);
        });
    }
}