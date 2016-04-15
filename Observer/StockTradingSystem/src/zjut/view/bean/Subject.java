package zjut.view.bean;

import java.util.ArrayList;
import java.util.Iterator;

abstract class Subject {
	
	ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public void notifObservers() {
		Iterator<Observer> iterator = observers.iterator();
		while (iterator.hasNext()) {
			iterator.next();
		}
	}
	public void addObserver(Observer observer) {
		observers.add(observer);
		
	}
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
}