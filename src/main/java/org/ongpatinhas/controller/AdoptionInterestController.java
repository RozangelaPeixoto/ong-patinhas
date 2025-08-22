package org.ongpatinhas.controller;

import jakarta.validation.Valid;
import org.ongpatinhas.dto.AdoptionInterestDTO;
import org.ongpatinhas.service.AdoptionInterestService;
import org.ongpatinhas.service.CaptchaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdoptionInterestController {

    private final AdoptionInterestService adoptionInterestService;
    private final CaptchaService captchaService;

    public AdoptionInterestController(AdoptionInterestService adoptionInterestService, CaptchaService captchaService) {
        this.adoptionInterestService = adoptionInterestService;
        this.captchaService = captchaService;
    }


    @GetMapping("/formulario-adocao")
    public String formAdoption(@ModelAttribute("adoptionInterest") AdoptionInterestDTO adoptionInterestDTO){
        return "formulario-adocao";
    }

    @PostMapping("/formulario-adocao")
    public String createAdoptionInterest(@Valid @ModelAttribute("adoptionInterest") AdoptionInterestDTO adoptionInterestDTO,
                                         BindingResult result,
                                         Model model,
                                         @RequestParam("g-recaptcha-response") String captchaResponse){
        boolean captchaValido = captchaService.validateCaptcha(captchaResponse);

        if (result.hasErrors() || !captchaValido) {
            if(!captchaValido) model.addAttribute("errorCaptcha", "Preencha o captcha");
            return "formulario-adocao";
        }

        adoptionInterestService.createAdoptionInterest(adoptionInterestDTO);
        model.addAttribute("successMessage", "Mensagem enviada com sucesso!");
        model.addAttribute("adoptionInterest", new AdoptionInterestDTO());

        return "formulario-adocao";
    }


}
