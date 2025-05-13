package com.example.reservas.dto;

import com.example.reservas.entity.Reserva;
import com.example.reservas.entity.Sala;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReservaDTO {
    private Long id;
    private String nomeResponsavel;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private Long salaId;

    public ReservaDTO(Reserva reserva) {
        this.id = reserva.getId();
        this.nomeResponsavel = reserva.getNomeResponsavel();
        this.dataHoraInicio = reserva.getDataHoraInicio();
        this.dataHoraFim = reserva.getDataHoraFim();
        this.salaId = reserva.getSala() != null ? reserva.getSala().getId() : null;
    }
}
