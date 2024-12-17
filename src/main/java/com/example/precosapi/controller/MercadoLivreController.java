package com.example.precosapi.controller;

import com.example.precosapi.dto.PrecoDTO;
import com.example.precosapi.service.MercadoLivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MercadoLivreController {

    @Autowired
    private MercadoLivreService mercadoLivreService;

    @GetMapping("/search")
    public PrecoDTO search(@RequestParam String keyword) {
        return mercadoLivreService.buscarPrecosCalculados(keyword);
    }
}
