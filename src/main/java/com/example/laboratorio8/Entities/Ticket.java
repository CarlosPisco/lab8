package com.example.laboratorio8.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idTipoTicket", nullable = false)
    private TipoTicketEvento idTipoTicket;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario idUsuario;

}