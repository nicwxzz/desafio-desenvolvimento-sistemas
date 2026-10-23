package br.com.senai.desafio.controller;

import br.com.senai.desafio.model.Servico;
import br.com.senai.desafio.repository.ServicoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoRepository repository;

    public ServicoController(ServicoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Servico criar(@Valid @RequestBody Servico servico) {
        return repository.save(servico);
    }

    @GetMapping
    public List<Servico> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Servico dados) {

        return repository.findById(id)
                .map(servico -> {
                    servico.setNome(dados.getNome());
                    servico.setDescricao(dados.getDescricao());
                    servico.setPreco(dados.getPreco());
                    servico.setDuracaoMinutos(dados.getDuracaoMinutos());

                    return ResponseEntity.ok(repository.save(servico));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
