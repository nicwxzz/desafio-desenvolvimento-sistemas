package br.com.senai.desafio.repository;

import br.com.senai.desafio.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
