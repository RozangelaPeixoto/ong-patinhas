package org.ongpatinhas.controller;

import jakarta.validation.Valid;
import org.ongpatinhas.dto.DonationDTO;
import org.ongpatinhas.service.MercadoPagoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FrontController {

    private final MercadoPagoService mercadoPagoService;

    public FrontController(MercadoPagoService mercadoPagoService) {
        this.mercadoPagoService = mercadoPagoService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/doacao")
    public String doacao(@ModelAttribute("donation") DonationDTO donationDTO) {
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

    @PostMapping("/doacao")
    public String checkout(@Valid @ModelAttribute("donation") DonationDTO donationDTO,
                           BindingResult result,
                           Model model) throws Exception {

        if (result.hasErrors()) {
            return "doacao";
        }

        String url = mercadoPagoService.createDonationPreference(donationDTO);
        return "redirect:"+url;
    }




}
