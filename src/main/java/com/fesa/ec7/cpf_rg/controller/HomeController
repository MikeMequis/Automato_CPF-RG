package com.fesa.ec7.cpf_rg.controller;

import com.fesa.ec7.cpf_rg.model.AFD;
import com.fesa.ec7.cpf_rg.model.Validator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/cpf_rg")
public class HomeController {

    private final AFD automaton;

    public HomeController() {
        automaton = new AFD();
        automaton.createAutomatum();
    }

    @GetMapping("/")
    public String home(Model model) {
        return "index"; // Retorna o template index.html
    }

    @PostMapping("/validacao")
    public String verificar(@RequestParam String cpf_rg, Model model) {
        boolean isValid = Validator.validar(cpf_rg, automaton); // Valida o CPF/RG
        model.addAttribute("cpf_rg", cpf_rg); // Passa o CPF/RG para o template
        model.addAttribute("valido", isValid); // Passa o resultado da validação para o template
        return "/validacao"; // Retorna o template validacao.html
    }
}