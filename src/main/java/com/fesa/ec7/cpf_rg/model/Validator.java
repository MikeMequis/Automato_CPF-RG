package com.fesa.ec7.cpf_rg.model;

public class Validator {
    public static boolean validar(String input, AFD automatum) {
        State currentState = automatum.getInitialState();
        
        for (char c : input.toCharArray()) {
            currentState = automatum.getNextState(currentState, c);
            if (currentState == State.INVALID) {
                return false;
            }
        }
        return automatum.isFinalState(currentState);
    }
}