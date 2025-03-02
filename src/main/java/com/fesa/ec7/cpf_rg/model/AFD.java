package com.fesa.ec7.cpf_rg.model;

import java.util.HashMap;
import java.util.Map;

public class AFD {
    private Map<String, State> transitions = new HashMap<>();
    private State initialState;
    private Map<State, Boolean> finalStates = new HashMap<>();

    public AFD() {}

    public void createAutomaton() {
        // Criando os estados
        State[] states = new State[28];
        for (int i = 0; i < 28; i++) {
            states[i] = new State(i);
        }

        // Definindo os estados finais
        finalStates.put(states[21], true);
        finalStates.put(states[22], true);
        finalStates.put(states[27], true);

        initialState = states[0];

        // Transições que leem dígitos (0-9)
        for (char c = '0'; c <= '9'; c++) {
            addTransition(states[0], c, states[1]);
            addTransition(states[1], c, states[2]);
            addTransition(states[2], c, states[4]);
            addTransition(states[3], c, states[6]);
            addTransition(states[4], c, states[5]);
            addTransition(states[4], c, states[13]);
            addTransition(states[13], c, states[14]);
            addTransition(states[14], c, states[15]);
            addTransition(states[5], c, states[9]);
            addTransition(states[9], c, states[10]);
            addTransition(states[10], c, states[11]);
            addTransition(states[6], c, states[7]);
            addTransition(states[7], c, states[8]);
            addTransition(states[8], c, states[12]);
            addTransition(states[12], c, states[16]);
            addTransition(states[16], c, states[18]);
            addTransition(states[18], c, states[17]);
            addTransition(states[15], c, states[19]);
            addTransition(states[19], c, states[17]);
            addTransition(states[17], c, states[22]);
            addTransition(states[20], c, states[21]);
            addTransition(states[22], c, states[25]);
            addTransition(states[25], c, states[27]);
            addTransition(states[23], c, states[24]);
            addTransition(states[24], c, states[17]);
            addTransition(states[26], c, states[25]);
        }

        // Transições com caracteres específicos
        addTransition(states[2], '.', states[3]);
        addTransition(states[4], '.', states[5]);
        addTransition(states[8], '.', states[12]);
        addTransition(states[11], '.', states[23]);

        addTransition(states[17], '-', states[20]);
        addTransition(states[22], '-', states[26]);

        addTransition(states[20], 'x', states[21]);
        addTransition(states[17], 'x', states[22]); 
    }

    protected void addTransition(State currentState, char character, State nextState) {
        transitions.put(currentState + ":" + character, nextState);
    }

    public State getNextState(State currentState, char character) {
        return transitions.getOrDefault(currentState + ":" + character, State.INVALID);
    }

    public boolean isFinalState(State state) {
        return finalStates.getOrDefault(state, false);
    }

    public State getInitialState() {
        return initialState;
    }
}