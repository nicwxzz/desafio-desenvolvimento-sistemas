package br.com.senai.desafio.controller;

import br.com.senai.desafio.model.Funcionario;
import br.com.senai.desafio.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioRepository repository;

    public FuncionarioController(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Funcionario criar(@Valid @RequestBody Funcionario funcionario) {
        return repository.save(funcionario);
    }

    @GetMapping
    public List<Funcionario> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Funcionario dados) {

        return repository.findById(id)
                .map(funcionario -> {
                    funcionario.setNome(dados.getNome());
                    funcionario.setCargo(dados.getCargo());
                    funcionario.setEmail(dados.getEmail());
                    funcionario.setDataAdmissao(dados.getDataAdmissao());

                    return ResponseEntity.ok(repository.save(funcionario));
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
