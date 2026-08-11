package com.example.demo.Controller;

import com.example.demo.model.Pet;
import com.example.demo.service.PetService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@RequestBody Pet cadastro) {

        return ResponseEntity.ok(petService.cadastrar(cadastro));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(petService.listarPorUsuario(usuarioId));
    }

    @GetMapping("/{petId}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long petId) {
        return ResponseEntity.ok(petService.buscarPorId(petId));
    }

    @PutMapping("/{petId}/{usuarioId}")
    public ResponseEntity<?> atualizar(@PathVariable Long petId, @PathVariable Long usuarioId, @RequestBody Pet dados) {

        return ResponseEntity.ok(petService.atualizar(petId, usuarioId, dados));
    }

    @DeleteMapping("/{petId}/{usuarioId}")
    public ResponseEntity<?> excluir(@PathVariable Long petId, @PathVariable Long usuarioId) {
        petService.excluir(petId, usuarioId);

        return ResponseEntity.ok("Pet excluído com sucesso.");
    }

}
