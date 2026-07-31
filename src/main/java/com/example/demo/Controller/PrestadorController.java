/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Controller;

import com.example.demo.model.Prestador;
import com.example.demo.service.PrestadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prestador")
public class PrestadorController {

    @Autowired
    private PrestadorService prestadorService;

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@RequestBody Prestador prestador) {
        return ResponseEntity.ok(prestadorService.cadastrar(prestador));
    }
}