package br.com.senai.desafio.service;

import br.com.senai.desafio.entity.Fornecedor;
import br.com.senai.desafio.entity.Produto;
import br.com.senai.desafio.repository.FornecedorRepository;
import br.com.senai.desafio.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final FornecedorRepository fornecedorRepository;

    public ProdutoService(ProdutoRepository produtoRepository,
                          FornecedorRepository fornecedorRepository) {
        this.produtoRepository = produtoRepository;
        this.fornecedorRepository = fornecedorRepository;
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public List<Produto> listarPorFornecedor(Long fornecedorId) {
        if (!fornecedorRepository.existsById(fornecedorId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado");
        }
        return produtoRepository.findByFornecedorId(fornecedorId);
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @Transactional
    public Produto cadastrar(Produto produto) {
        produto.setId(null);
        produto.setFornecedor(buscarFornecedor(produto.getFornecedor()));
        return produtoRepository.save(produto);
    }

    @Transactional
    public Produto atualizar(Long id, Produto dados) {
        Produto atual = buscarPorId(id);
        Fornecedor fornecedor = buscarFornecedor(dados.getFornecedor());
        atual.setNome(dados.getNome());
        atual.setDescricao(dados.getDescricao());
        atual.setPreco(dados.getPreco());
        atual.setEstoque(dados.getEstoque());
        atual.setFornecedor(fornecedor);
        return produtoRepository.save(atual);
    }

    @Transactional
    public void excluir(Long id) {
        Produto produto = buscarPorId(id);
        produtoRepository.delete(produto);
    }

    private Fornecedor buscarFornecedor(Fornecedor informado) {
        if (informado == null || informado.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Informe o ID do fornecedor no campo fornecedor.id");
        }
        return fornecedorRepository.findById(informado.getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Fornecedor informado não encontrado"));
    }
}
