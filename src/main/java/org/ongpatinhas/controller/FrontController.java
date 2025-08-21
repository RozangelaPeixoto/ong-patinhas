package org.ongpatinhas.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.ongpatinhas.dto.AdoptionInterestDTO;
import org.ongpatinhas.dto.DonationDTO;
import org.ongpatinhas.service.AdoptionInterestService;
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
    private final AdoptionInterestService adoptionInterestService;

    public FrontController(MercadoPagoService mercadoPagoService, AdoptionInterestService adoptionInterestService) {
        this.mercadoPagoService = mercadoPagoService;
        this.adoptionInterestService = adoptionInterestService;
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
                           Model model) {

        if (result.hasErrors()) {
            return "doacao";
        }

        String url = mercadoPagoService.createDonationPreference(donationDTO);
        return "redirect:"+url;
    }

    @GetMapping({"/sucesso", "/cancelado", "/pendente"})
    public String handleRequest(HttpServletRequest request, Model model) {
        String path = request.getRequestURI();
        String status = path.substring(1,2).toUpperCase().concat(path.substring(2));

        model.addAttribute("status", status);

        String message = switch (path) {
            case "/sucesso" -> "Obrigada pela sua doação";
            case "/cancelado" -> "Parece que ocorreu algum problema com a sua doação.";
            case "/pendente" -> "Sua doação está pendente.";
            default ->  "Status não encontrado";
        };

        model.addAttribute("message", message);

        return "retorno-pagamento";
    }

    @GetMapping("/formulario-adocao")
    public String formAdoption(@ModelAttribute("adoptionInterest") AdoptionInterestDTO adoptionInterestDTO){
        return "formulario-adocao";
    }

    @PostMapping("/formulario-adocao")
    public String createAdoptionInterest(@Valid @ModelAttribute("adoptionInterest") AdoptionInterestDTO adoptionInterestDTO,
                               BindingResult result,
                               Model model){

        if (result.hasErrors()) {
            return "formulario-adocao";
        }

        adoptionInterestService.createAdoptionInterest(adoptionInterestDTO);
        model.addAttribute("successMessage", "Mensagem enviada com sucesso!");
        model.addAttribute("adoptionInterest", new AdoptionInterestDTO());

        return "formulario-adocao";
    }

}
