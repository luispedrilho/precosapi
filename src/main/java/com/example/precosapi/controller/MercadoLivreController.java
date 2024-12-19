package com.example.precosapi.controller;

import com.example.precosapi.dto.ItemResponseDTO;
import com.example.precosapi.dto.PrecoDTO;
import com.example.precosapi.service.MercadoLivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MercadoLivreController {

    @Autowired
    private MercadoLivreService mercadoLivreService;

    // Endpoint para buscar preços calculados
    @GetMapping("/search")
    public PrecoDTO search(@RequestParam String keyword) {
        return mercadoLivreService.buscarPrecosCalculados(keyword);
    }

    @CrossOrigin(origins = {"http://localhost:3000", "https://luispedrilho.github.io/"})
    // Novo endpoint para retornar o primeiro item com as informações desejadas
    @GetMapping("/first-item")
    public ItemResponseDTO getFirstItem(@RequestParam String keyword) {
        return mercadoLivreService.getFirstItemDetails(keyword);
    }

    // Novo endpoint /catalog_product que retorna os catalog_product_id com os detalhes
    @CrossOrigin(origins = {"http://localhost:3000", "https://luispedrilho.github.io/"})
    @GetMapping("/catalog_product")
    public List<ItemResponseDTO> getCatalogProductIds(@RequestParam String keyword) {
        return mercadoLivreService.getCatalogProductIds(keyword);
    }
}
