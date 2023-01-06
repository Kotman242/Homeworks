/*
класс ShoppingCart - константа MAX_SIZE_SHOPPING_CART  магическое число
класс ShoppingCart  -  метод printShoppingCart()   DRY, не дублирует код вывод общей корзины и любимого набора товаров

класс Wallet и ShoppingCart  отражает принцип единственной ответственности  - класс выполняет только функции кошелька покупателя.
класс Product - принцип открытости/закрытости доступно добавление новых типо продуктов, без изменения класса родителя.
класс Grocery - принцип замены Барбары Лисков
интерфейс Payable отражает принцип сегрегации (разделения) интерфейса, далее может быть не только кошелек, но и счет и т.п.
класс Product - принцип инверсии зависимостей, любой продоваемый товар может быть продуктов (услуга, дверь, машина, сыр).
*/

public class Main {
    public static void main(String[] args) {
        Product cheese = new Grocery(10, "cheese", 14);
        Product milk = new Grocery(5, "milk", 5);
        Product mazda = new Car(1000, "mazda 6", 2021);
        Client client = new Client("Олег");
        client.addProduct(cheese, 5);
        client.addProduct(milk, 2);
        client.addProduct(mazda, 1);
        client.addWallet(10000);
        client.removeProductFromShoppingCart(milk, 1);
        client.addfavouriteShoppingCart();
        client.pay(client.getShoppingCart().sum());
        System.out.println(client.balance());
        client.getShoppingCart().printShoppingCart();
        client.getFavouriteShoppingCart().printShoppingCart();
    }
}
