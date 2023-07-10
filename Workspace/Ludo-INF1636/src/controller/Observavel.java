package controller;

import view.Observador;

public interface Observavel {
    void addObservador(Observador observer);
    void removeObservador(Observador observer);
    void atualizaObservadores();
    public Object get();
}