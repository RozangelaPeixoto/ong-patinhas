package org.ongpatinhas.controller;

import org.ongpatinhas.dto.DogDTO;
import org.ongpatinhas.service.DogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FrontController {

    private final DogService dogService;

    public FrontController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/adocao")
    public String adocao(Model model) {
        List<DogDTO> dogs = dogService.findAllDogs();
        model.addAttribute("dogs", dogs);

        return "adocao";
    }

    @GetMapping("/quemsomos")
    public String quemSomos() {
        return "quemsomos";
    }

    @GetMapping("/mensagem")
    public String message() {
        return "mensagem";
    }

}
