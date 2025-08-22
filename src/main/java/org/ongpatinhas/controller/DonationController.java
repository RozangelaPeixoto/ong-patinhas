package org.ongpatinhas.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.ongpatinhas.dto.DonationDTO;
import org.ongpatinhas.service.CaptchaService;
import org.ongpatinhas.service.MercadoPagoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DonationController {

    private final MercadoPagoService mercadoPagoService;
    private final CaptchaService captchaService;

    public DonationController(MercadoPagoService mercadoPagoService, CaptchaService captchaService) {
        this.mercadoPagoService = mercadoPagoService;
        this.captchaService = captchaService;
    }

    @GetMapping("/doacao")
    public String doacao(@ModelAttribute("donation") DonationDTO donationDTO) {
        return "doacao";
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
}
