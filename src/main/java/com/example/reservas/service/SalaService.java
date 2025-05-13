package com.example.reservas.service;

import com.example.reservas.dto.SalaDTO;
import com.example.reservas.entity.Sala;
import com.example.reservas.repository.SalaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {
    @Autowired
    private SalaRepository salaRepository;

    @Transactional(readOnly = true)
    public List<SalaDTO> findAll() {
        List <Sala> salas = salaRepository.findAll();
        return salas.stream().map(SalaDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public SalaDTO findById(Long id) {
        Sala sala = salaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sala com id " + id + " não encontrada."));
        return new SalaDTO(sala);
    }

    @Transactional
    public SalaDTO insert (SalaDTO salaDTO) {
        Sala sala = new Sala();
        copyEntityDTO(sala,salaDTO);
        sala = salaRepository.save(sala);
        return new SalaDTO(sala);
    }

    @Transactional
    public SalaDTO update(Long id, SalaDTO salaDTO) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sala com id " + id + " não encontrada."));
        copyEntityDTO(sala,salaDTO);
        sala = salaRepository.save(sala);
        return new SalaDTO(sala);
    }

    @Transactional
    public void delete (Long, id) {
        salaRepository.deleteById(id);
    }

    private void copyEntityDTO(Sala sala, SalaDTO salaDTO) {
        sala.setId(salaDTO.getId());
        sala.setNome(salaDTO.getNome());
        sala.setCapacidadeMaxima(salaDTO.getCapacidadeMaxima());
        sala.setLocalizacao(salaDTO.getLocalizacao());
    }
}
