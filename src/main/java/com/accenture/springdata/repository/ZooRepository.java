package com.accenture.springdata.repository;

import com.accenture.springdata.model.Zoo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZooRepository extends JpaRepository<Zoo, Long> {
    Zoo findByNombre(String nombre);

    Zoo getById(Long id);

}
