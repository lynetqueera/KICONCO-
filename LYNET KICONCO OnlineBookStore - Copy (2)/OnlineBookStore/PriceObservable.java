package OnlineBookStore;

import java.util.List;
import java.util.ArrayList;

// PriceObservable Subject
public class PriceObservable {
    private List<PriceObserver> observers;
    private Product product;

    public PriceObservable(Product product) {
        this.observers = new ArrayList<>();
        this.product = product;
    }

    public void addObserver(PriceObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(PriceObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(double oldPrice, double newPrice) {
        for (PriceObserver observer : observers) {
            observer.update(product, oldPrice, newPrice);
        }
    }

    public void setPrice(double newPrice) {
        double oldPrice = product.getPrice();
        product.setPrice(newPrice);
        notifyObservers(oldPrice, newPrice);
    }

    public void setObservers(List<PriceObserver> observers) {
        this.observers = observers;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}