package com.custodio.denunciaservice.repository;

import com.custodio.denunciaservice.entity.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DenunciaRepository extends JpaRepository<Denuncia, Integer> {
    List<Denuncia> findByDniOrderByFechaDesc(String dni);
}