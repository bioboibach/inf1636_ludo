package controller;

public interface Observable {
	int state = 0;
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
    public Object get();
}