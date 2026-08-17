package com.example.demo.service;

import com.example.demo.model.Prestador;
import com.example.demo.model.Usuario;
import com.example.demo.repository.PrestadorRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioR;

    @Autowired
    private TokenService Token;

    @Autowired
    private PrestadorRepository prestadorRepository;

    public String Logar(String email, String senha) {
        Usuario user = usuarioR.findByEmail(email).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email invalido"));

        if (!senha.equals(user.getSenha())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha invalido");
        }

        return Token.gerarToken(user);
    }

    public String Registro(Usuario cadastro) {

        if (cadastro.getNome() == null || cadastro.getNome().isBlank()) {
            throw new RuntimeException("Nome é obrigatório.");
        }

        if (cadastro.getEmail() == null || !cadastro.getEmail().matches("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            throw new RuntimeException("Informe um email válido.");
        }

        if (cadastro.getSenha() == null || cadastro.getSenha().isBlank()) {
            throw new RuntimeException("Senha é obrigatória.");
        }

        if (cadastro.getSenha().length() < 6) {
            throw new RuntimeException("A senha deve ter no mínimo 6 caracteres!");
        }

        if (cadastro.getTelefone() == null || cadastro.getTelefone().isBlank()) {
            throw new RuntimeException("O telefone é obrigatório!");
        }

        if (!cadastro.getTelefone().matches("\\d+")) {
            throw new RuntimeException("Telefone deve conter apenas números.");
        }

        if (cadastro.getRua() == null || cadastro.getRua().isBlank()) {
            throw new RuntimeException("A rua é obrigatória!");
        }

        if (cadastro.getBairro() == null || cadastro.getBairro().isBlank()) {
            throw new RuntimeException("O bairro é obrigatório!");
        }

        if (cadastro.getCidade() == null || cadastro.getCidade().isBlank()) {
            throw new RuntimeException("Cidade é obrigatória.");
        }

        if (cadastro.getEstado() == null || cadastro.getEstado().isBlank()) {
            throw new RuntimeException("O estado é obrigatório!");
        }

        if (cadastro.getTipoUsuario() == null) {
            throw new RuntimeException("O tipo de usuário é obrigatório!");
        }

        if (usuarioR.existsByEmail(cadastro.getEmail())) {
            throw new RuntimeException("Já existe um usuário cadastrado com este email!");
        }

        Usuario user = new Usuario();

        user.setNome(cadastro.getNome());
        user.setEmail(cadastro.getEmail());
        user.setSenha(cadastro.getSenha());
        user.setTelefone(cadastro.getTelefone());
        user.setRua(cadastro.getRua());
        user.setBairro(cadastro.getBairro());
        user.setCidade(cadastro.getCidade());
        user.setEstado(cadastro.getEstado());
        user.setTipoUsuario(cadastro.getTipoUsuario());

        if (cadastro.getTipoResidencia() == null) {
            user.setTipoResidencia(Usuario.TipoResidencia.APARTAMENTO);
        } else {
            user.setTipoResidencia(cadastro.getTipoResidencia());
        }

        usuarioR.save(user);

        return Token.gerarToken(user);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioR.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }
}