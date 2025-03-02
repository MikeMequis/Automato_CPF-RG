package com.fesa.ec7.cpf_rg.model;

public class Validator {
    public static String validar(String input, AFD automaton) {
        State currentState = automaton.getInitialState();
        
        for (char c : input.toCharArray()) {
            currentState = automaton.getNextState(currentState, c);
            if (currentState == State.INVALID) {
                return "Inválido";
            }
        }

        if (automaton.isFinalState(currentState)) {
            if (currentState.getId() == 21 || currentState.getId() == 22) {
                return "RG";
            } else if (currentState.getId() == 27) {
                return "CPF";
            }
        }

        return "Inválido";
    }
}