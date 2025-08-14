package org.ongpatinhas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/doacao")
    public String doacao() {
        return "doacao";
    }

    @GetMapping("/adocao")
    public String adocao() {
        return "adocao";
    }

    @GetMapping("/quemsomos")
    public String quemSomos() {
        return "quemsomos";
    }






}
