package zjut.view.model;

public interface Subject {
	public void notifObservers();
	public void addObserver(Observer observer) ;
	public void removeObserver(Observer observer);
}