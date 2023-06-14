package com.example.laboratorio8.Repository;

import com.example.laboratorio8.Entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento,Integer> {
}
