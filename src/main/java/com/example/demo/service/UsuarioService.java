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

if (user.getTipoUsuario() == Usuario.TipoUsuario.PRESTADOR
        || user.getTipoUsuario() == Usuario.TipoUsuario.AMBOS) {

    Prestador prestador = new Prestador();

    prestador.setUsuario(user);
    prestador.setAceitaHospedagem(false);
    prestador.setAceitaPasseio(false);
    prestador.setAceitaBanho(false);
    prestador.setAceitaPequeno(false);
    prestador.setAceitaMedio(false);
    prestador.setAceitaGrande(false);
    prestador.setAceitaGigante(false);
    prestador.setDescricao("");
    prestador.setValorHora(0.0);

    prestadorRepository.save(prestador);
}

return Token.gerarToken(user);}
}