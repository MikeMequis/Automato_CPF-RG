package com.fesa.ec7.cpf_rg.controller;

import com.fesa.ec7.cpf_rg.model.AFD;
import com.fesa.ec7.cpf_rg.model.Validator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cpf_rg")
public class HomeController {

    private final AFD automaton;

    public HomeController() {
        automaton = new AFD();
        automaton.createAutomaton();
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/validacao")
    public String verificar(@RequestParam String cpf_rg, Model model) {
        String tipoDocumento = Validator.validar(cpf_rg, automaton);
        
        model.addAttribute("cpf_rg", cpf_rg);
        model.addAttribute("tipoDocumento", tipoDocumento);
        model.addAttribute("valido", !tipoDocumento.equals("Inválido"));

        return "validacao";
    }
}