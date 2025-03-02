package com.fesa.ec7.cpf_rg.model;

public class State {
    private int id;

    // Constante para representar estado inválido
    public static final State INVALID = new State(-1);

    public State(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "q" + id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        State state = (State) obj;
        return id == state.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
