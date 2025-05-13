package com.example.reservas.service;

import com.example.reservas.dto.ReservaDTO;
import com.example.reservas.dto.SalaDTO;
import com.example.reservas.entity.Sala;
import com.example.reservas.entity.Reserva;
import com.example.reservas.repository.ReservaRepository;
import com.example.reservas.repository.SalaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private SalaRepository salaRepository;

    @Transactional(readOnly = true)
    public List<ReservaDTO> findAll() {
        List<Reserva> reservas = reservaRepository.findAll();
        return reservas.stream().map(ReservaDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public List<ReservaDTO> findBySalaId(Long salaId) {
        List<Reserva> reservas = reservaRepository.findBySalaId(salaId);
        return reservas.stream().map(ReservaDTO::new).toList();
    }

    @Transactional
    public ReservaDTO insert(ReservaDTO reservaDTO) {
        if (reservaDTO.getDataHoraInicio().isAfter(reservaDTO.getDataHoraFim()) ||
                reservaDTO.getDataHoraInicio().isEqual(reservaDTO.getDataHoraFim())) {
            throw new IllegalArgumentException("A data e hora de início deve ser anterior à data e hora de término.");
        }

        if (reservaDTO.getDataHoraInicio().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Não é possível reservar uma sala para o passado.");
        }

        Sala sala = salaRepository.findById(reservaDTO.getSalaId())
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));

        List<Reserva> reservasExistentes = reservaRepository.findBySalaId(sala.getId());

        boolean conflito = reservasExistentes.stream().anyMatch(r ->
                reservaDTO.getDataHoraInicio().isBefore(r.getDataHoraFim()) && reservaDTO.getDataHoraFim().isAfter(r.getDataHoraInicio()));

        if (conflito) {
            throw new IllegalArgumentException("A sala já está reservada nesse horário.");
        }

        Reserva reserva = new Reserva();
        reserva.setId(reservaDTO.getId());
        reserva.setNomeResponsavel(reservaDTO.getNomeResponsavel());
        reserva.setDataHoraInicio(reservaDTO.getDataHoraInicio());
        reserva.setDataHoraFim(reservaDTO.getDataHoraFim());
        reserva.setSala(sala);
    }

    @Transactional
    public void delete(Long id) {
        reservaRepository.deleteById(id);
    }
}



