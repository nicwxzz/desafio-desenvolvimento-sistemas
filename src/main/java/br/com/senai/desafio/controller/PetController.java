package br.com.senai.desafio.controller;

import br.com.senai.desafio.entity.Pet;
import br.com.senai.desafio.service.PetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pet cadastrar(@Valid @RequestBody Pet pet) {
        return petService.cadastrar(pet);
    }

    @GetMapping
    public List<Pet> listarTodos() {
        return petService.listarTodos();
    }

    @GetMapping("/{id}")
    public Pet buscarPorId(@PathVariable Long id) {
        return petService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Pet atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Pet pet) {
        return petService.atualizar(id, pet);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        petService.excluir(id);
    }
}
