public class Product {

    // принцип инверсии зависимостей, любой продоваемый товар может быть продуктов (услуга, дверь, машина, сыр).
    //принцип открытости/закрытости доступно добавление новых типо продуктов, без изменения класса родителя.
    private int price;
    private String name;

    public Product(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " цена " + price;
    }
}
