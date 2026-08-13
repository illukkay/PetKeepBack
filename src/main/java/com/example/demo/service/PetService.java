package com.example.demo.service;

import com.example.demo.model.Pet;
import com.example.demo.model.Usuario;
import com.example.demo.repository.PetRepository;
import com.example.demo.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Pet cadastrar(Pet cadastro) {

        Usuario usuario = usuarioRepository.findById(cadastro.getTutor().getId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        cadastro.setTutor(usuario);
        return petRepository.save(cadastro);
    }

    public List<Pet> listarPorUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        return petRepository.findByTutorId(usuario.getId());
    }

    public Pet buscarPorId(Long petId) {
        return petRepository.findById(petId).orElseThrow(() -> new RuntimeException("Pet não encontrado."));
    }

    public Pet atualizar(Long petId, Long usuarioId, Pet dados) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new RuntimeException("Pet não encontrado."));

        if (!pet.getTutor().getId().equals(usuarioId)) {
            throw new RuntimeException("Você não pode alterar um pet que não pertence a você.");
        }

        pet.setNome(dados.getNome());
        pet.setEspecie(dados.getEspecie());
        pet.setRaca(dados.getRaca());
        pet.setIdade(dados.getIdade());
        pet.setPorte(dados.getPorte());
        pet.setObservacoes(dados.getObservacoes());
        return petRepository.save(pet);
    }

    public void excluir(Long petId, Long usuarioId) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new RuntimeException("Pet não encontrado."));
        if (!pet.getTutor().getId().equals(usuarioId)) {
            throw new RuntimeException("Você não pode excluir um pet que não pertence a você.");
        }
        petRepository.delete(pet);
    }
}
