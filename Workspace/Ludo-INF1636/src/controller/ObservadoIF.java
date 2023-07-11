package controller;

import java.util.ArrayList;
import java.util.List;


public interface ObservadoIF {	
	void addObservador(ObservadorIF o);
    void removeObservador(ObservadorIF observer);
    void atualizaObservadores();
    public Object get();
}


