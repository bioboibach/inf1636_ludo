package controller;


public interface Observer {
    void notify(Observable o);
}

//Observer concreto
//public class Observer implements ObserverInterface {
//    public void notify(Observable o) {
//        System.out.println("Received state update: ");
//    }
//}
