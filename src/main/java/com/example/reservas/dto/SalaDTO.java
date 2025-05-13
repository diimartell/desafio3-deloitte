package com.example.reservas.dto;

import com.example.reservas.entity.Sala;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class SalaDTO {
    private Long id;
    private String nome;
    private Integer capacidadeMaxima;
    private String localizacao;

    public SalaDTO(Sala sala) {
        this.id = sala.getId();
        this.nome = sala.getNome();
        this.capacidadeMaxima = sala.getCapacidadeMaxima();
        this.localizacao = sala.getLocalizacao();
    }
}
