package OnlineBookStore;

// Price Observer Interface)
public interface PriceObserver {
    void update(Product product, double oldPrice, double newPrice);
}
