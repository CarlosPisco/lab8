package com.example.laboratorio8.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "evento")
@JsonIgnoreProperties({"path_image"})
public class Evento {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fecha", nullable = false)
    private Instant fecha;

    @Column(name = "nombre", nullable = false, length = 80)
    private String nombre;

    @Column(name = "descripcion", length = 400)
    private String descripcion;

    @Column(name = "path_image", length = 100)
    private String pathImage;

    @ManyToOne( optional = false)
    @JoinColumn(name = "id_local", nullable = false)
    private Local idLocal;

}