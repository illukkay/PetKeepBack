/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aluno
 */
@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioService UserService;

    @PostMapping("/logar")
    public String Logar(@RequestBody Usuario user) {
        return UserService.Logar(user.getEmail(), user.getSenha());

    }

    @PostMapping("/registro")
    public String Registro(@RequestBody Usuario user) {
        return UserService.Registro(user);

    }

}
