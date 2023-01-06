public class Grocery extends Product {

    // принцип замены Барбары Лисков
    private int expirationDate;

    public Grocery(int price, String name, int expirationDate) {
        super(price, name);
        this.expirationDate = expirationDate;
    }

    public int getExpirationDate() {
        return expirationDate;
    }
}
