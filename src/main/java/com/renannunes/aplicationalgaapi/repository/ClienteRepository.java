package com.renannunes.aplicationalgaapi.repository;

import com.renannunes.aplicationalgaapi.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNome(String name);

    Cliente findByEmail(String email);

}
