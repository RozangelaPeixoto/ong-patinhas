package org.ongpatinhas.controller;

import org.ongpatinhas.dto.DonationDTO;
import org.ongpatinhas.service.DonationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FrontController {

    private final DonationService donationService;

    public FrontController(DonationService donationService) {
        this.donationService = donationService;
    }

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

    @PostMapping("/adocao")
    public String checkout(DonationDTO donationDTO) throws Exception {
        String url = donationService.createPreference(donationDTO);
        return "redirect:"+url;
    }




}
