package br.com.senai.desafio.service;

import br.com.senai.desafio.entity.Fornecedor;
import br.com.senai.desafio.repository.FornecedorRepository;
import br.com.senai.desafio.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FornecedorService {
    private final FornecedorRepository fornecedorRepository;
    private final ProdutoRepository produtoRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository,
                             ProdutoRepository produtoRepository) {
        this.fornecedorRepository = fornecedorRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll();
    }

    public Fornecedor buscarPorId(Long id) {
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Fornecedor não encontrado"));
    }

    @Transactional
    public Fornecedor cadastrar(Fornecedor fornecedor) {
        fornecedor.setId(null);
        if (fornecedorRepository.existsByCnpj(fornecedor.getCnpj())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe fornecedor com esse CNPJ");
        }
        return fornecedorRepository.save(fornecedor);
    }

    @Transactional
    public Fornecedor atualizar(Long id, Fornecedor dados) {
        Fornecedor atual = buscarPorId(id);
        if (fornecedorRepository.existsByCnpjAndIdNot(dados.getCnpj(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe outro fornecedor com esse CNPJ");
        }
        atual.setNome(dados.getNome());
        atual.setCnpj(dados.getCnpj());
        atual.setEmail(dados.getEmail());
        atual.setTelefone(dados.getTelefone());
        return fornecedorRepository.save(atual);
    }

    @Transactional
    public void excluir(Long id) {
        Fornecedor fornecedor = buscarPorId(id);
        if (produtoRepository.existsByFornecedorId(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Não é possível excluir o fornecedor porque existem produtos vinculados a ele");
        }
        fornecedorRepository.delete(fornecedor);
    }
}
