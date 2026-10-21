package br.com.senai.desafio.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pets")
@Getter
@Setter
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do pet é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "A espécie é obrigatória")
    @Size(max = 50, message = "A espécie deve ter no máximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String especie;

    @NotBlank(message = "A raça é obrigatória")
    @Size(max = 80, message = "A raça deve ter no máximo 80 caracteres")
    @Column(nullable = false, length = 80)
    private String raca;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
}
