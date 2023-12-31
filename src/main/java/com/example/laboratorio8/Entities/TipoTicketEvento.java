package com.example.laboratorio8.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tipo_ticket_evento")
public class TipoTicketEvento {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "precio", length = 45)
    private String precio;

    @Column(name = "cantidad")
    private Integer cantidad;

    @ManyToOne( optional = false)
    @JoinColumn(name = "idEvento", nullable = false)
    private Evento idEvento;

}