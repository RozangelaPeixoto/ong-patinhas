package org.ongpatinhas.controller;

import jakarta.validation.Valid;
import org.ongpatinhas.dto.AdoptionInterestDTO;
import org.ongpatinhas.dto.DogDTO;
import org.ongpatinhas.service.AdoptionInterestService;
import org.ongpatinhas.service.CaptchaService;
import org.ongpatinhas.service.DogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdoptionInterestController {

    private final AdoptionInterestService adoptionInterestService;
    private final CaptchaService captchaService;
    private final DogService dogService;

    public AdoptionInterestController(AdoptionInterestService adoptionInterestService, CaptchaService captchaService, DogService dogService) {
        this.adoptionInterestService = adoptionInterestService;
        this.captchaService = captchaService;
        this.dogService = dogService;
    }

    @GetMapping("/formulario-adocao/{id}")
    public String formAdoption(@PathVariable("id") String id,
                               @ModelAttribute("adoptionInterest") AdoptionInterestDTO adoptionInterestDTO,
                               Model model){

        DogDTO dto = dogService.findInfoDogById(id);

        model.addAttribute("dog", dto);
        return "formulario-adocao";
    }

    @PostMapping("/formulario-adocao")
    public String createAdoptionInterest(@Valid @ModelAttribute("adoptionInterest") AdoptionInterestDTO adoptionInterestDTO,
                                         BindingResult result,
                                         Model model,
                                         @RequestParam("g-recaptcha-response") String captchaResponse){

        boolean captchaValido = captchaService.validateCaptcha(captchaResponse);

        DogDTO dto = dogService.findInfoDogById(adoptionInterestDTO.dogId());
        model.addAttribute("dog", dto);

        if (result.hasErrors() || !captchaValido) {
            if(!captchaValido) model.addAttribute("errorCaptcha", "Preencha o captcha");
            return "formulario-adocao";
        }

        adoptionInterestService.createAdoptionInterest(adoptionInterestDTO);
        model.addAttribute("successMessage", "Mensagem enviada com sucesso!");
        model.addAttribute("adoptionInterest", new AdoptionInterestDTO());

        return "mensagem";
    }

}
