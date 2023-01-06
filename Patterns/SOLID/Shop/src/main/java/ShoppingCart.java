import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private final static int MAX_SIZE_SHOPPING_CART = 10;   //Магическое число
    private Map<Product, Integer> shoppingCart;

    public ShoppingCart() {
        this.shoppingCart = new HashMap();
    }

    public void addProduct(Product product, Integer number) {
        if (shoppingCart.size() >= MAX_SIZE_SHOPPING_CART) {
            System.out.println("Корзина полна.");
        } else {
            if (shoppingCart.containsKey(product)) {
                shoppingCart.put(product, shoppingCart.get(product) + number);
            } else {
                shoppingCart.put(product, number);
            }
        }
    }

    public void removeProduct(Product product, Integer number) {
        if (shoppingCart.get(product) <= number) {
            shoppingCart.remove(product);
        } else {
            shoppingCart.put(product, shoppingCart.get(product) - number);
        }
    }

    public void printShoppingCart() {
        for (Product product : shoppingCart.keySet()) {
            System.out.println(product + " кол-во " + shoppingCart.get(product));
        }
    }

    public int sum() {
        int sum = 0;
        for (Map.Entry<Product, Integer> entry : shoppingCart.entrySet()) {
            sum += entry.getKey().getPrice() * entry.getValue();
        }
        return sum;
    }
}
