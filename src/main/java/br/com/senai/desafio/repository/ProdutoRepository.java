package br.com.senai.desafio.repository;

import br.com.senai.desafio.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByFornecedorId(Long fornecedorId);
    boolean existsByFornecedorId(Long fornecedorId);
}
