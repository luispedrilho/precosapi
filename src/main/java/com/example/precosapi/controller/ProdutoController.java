package com.example.precosapi.controller;

import com.example.precosapi.dto.PrecoDTO;
import com.example.precosapi.service.PrecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/precos")
// Permite requisições do front-end
@CrossOrigin(origins = {"http://localhost:3000", "https://luispedrilho.github.io/"})
public class ProdutoController {

    @Autowired
    private PrecoService precoService;

    @PostMapping
    public ResponseEntity<PrecoDTO> buscarPrecos(@RequestBody ProdutoRequest request) {
        PrecoDTO precos = precoService.calcularPrecos(request.getKeyword(), request.getStatus());
        return ResponseEntity.ok(precos);
    }
}

// Classe interna para capturar os parâmetros recebidos
class ProdutoRequest {
    private String keyword;
    private String status;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}