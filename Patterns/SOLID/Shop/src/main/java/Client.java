public class Client {

    private String name;
    private Payable payable;
    private ShoppingCart favouriteShoppingCart;
    private ShoppingCart shoppingCart;

    public Client(String name) {
        this.name = name;
        payable = new Wallet();
        shoppingCart = new ShoppingCart();
    }

    public void pay(int sum) {
        if (payable.pay(sum)) {
            System.out.println("Покупка савершена");
            shoppingCart = new ShoppingCart();
        } else {
            System.out.println("Недостаточно средств");
        }
    }

    public void addWallet(int sum) {
        payable.add(sum);
    }

    public void addProduct(Product product, int number) {
        shoppingCart.addProduct(product, number);
    }

    public void removeProductFromShoppingCart(Product product, int number) {
        shoppingCart.removeProduct(product, number);
    }

    public void addfavouriteShoppingCart() {
        favouriteShoppingCart = shoppingCart;
    }

    public int balance() {
        return payable.getBalance();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShoppingCart getFavouriteShoppingCart() {
        return favouriteShoppingCart;
    }

    public void setFavouriteShoppingCart(ShoppingCart favouriteShoppingCart) {
        this.favouriteShoppingCart = favouriteShoppingCart;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
