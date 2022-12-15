import java.util.Objects;

public class PersonBuilder {

    protected String name = null;
    protected String surname = null;
    private int age = -1;
    private String address = null;

    public PersonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonBuilder setAge(int age) {
        if (age < 0) throw new IllegalArgumentException("Возраст не может быть отрицаетльным");
        this.age = age;
        return this;
    }

    public PersonBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public Person build() {
        if (surname == null) throw new IllegalArgumentException("Не указана фамилия");
        if (name == null) throw new IllegalArgumentException("Не указано имя");
        if (age == -1 && address == null) return new Person(name, surname);
        if (age == -1 && address != null) return new Person(name, surname, address);
        if (age != -1 && address == null) return new Person(name, surname, age);
        return new Person(name, surname, age, address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonBuilder that = (PersonBuilder) o;
        return age == that.age && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age, address);
    }

}
