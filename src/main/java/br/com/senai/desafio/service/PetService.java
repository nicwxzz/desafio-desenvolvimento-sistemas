package br.com.senai.desafio.service;

import br.com.senai.desafio.entity.Cliente;
import br.com.senai.desafio.entity.Pet;
import br.com.senai.desafio.repository.ClienteRepository;
import br.com.senai.desafio.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final ClienteRepository clienteRepository;

    public PetService(
            PetRepository petRepository,
            ClienteRepository clienteRepository) {
        this.petRepository = petRepository;
        this.clienteRepository = clienteRepository;
    }

    public Pet cadastrar(Pet pet) {
        Long clienteId = pet.getCliente().getId();

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Cliente não encontrado com ID: " + clienteId));

        pet.setCliente(cliente);

        return petRepository.save(pet);
    }

    public List<Pet> listarTodos() {
        return petRepository.findAll();
    }

    public Pet buscarPorId(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Pet não encontrado com ID: " + id));
    }

    public Pet atualizar(Long id, Pet dadosAtualizados) {
        Pet pet = buscarPorId(id);

        Long clienteId = dadosAtualizados.getCliente().getId();

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Cliente não encontrado com ID: " + clienteId));

        pet.setNome(dadosAtualizados.getNome());
        pet.setEspecie(dadosAtualizados.getEspecie());
        pet.setRaca(dadosAtualizados.getRaca());
        pet.setCliente(cliente);

        return petRepository.save(pet);
    }

    public void excluir(Long id) {
        Pet pet = buscarPorId(id);
        petRepository.delete(pet);
    }
}
