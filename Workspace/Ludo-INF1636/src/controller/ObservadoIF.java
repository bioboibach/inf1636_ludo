package controller;

public interface ObservadoIF {	
	void addObservador(ObservadorIF o);
    void removeObservador(ObservadorIF observer);
    void atualizaObservadores();
    public void get();
}


