package com.example.laboratorio8.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 80)
    private String nombre;

    @Column(name = "ruc", length = 50)
    private String ruc;

}