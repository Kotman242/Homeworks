public class Car extends Product {

    private int yearOfProduction;

    public Car(int price, String name, int yearOfProduction) {
        super(price, name);
        this.yearOfProduction = yearOfProduction;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }
}
