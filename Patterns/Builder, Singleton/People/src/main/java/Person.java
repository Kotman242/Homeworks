import java.util.Objects;

public class Person {

    protected final String name;
    protected final String surname;
    private int age = -1;
    private String address = null;

    public Person(String name, String surname, int age, String address) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.address = address;
    }

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Person(String name, String surname, String address) {
        this.name = name;
        this.surname = surname;
        this.address = address;
    }


    public void happyBirthday() {
        age++;
    }

    public boolean hasAge() {
        return age != -1;
    }

    public boolean hasAddress() {
        return address != null;
    }

    public PersonBuilder newChildBuilder() {
        PersonBuilder child = new PersonBuilder();
        child.setSurname(surname);
        if (hasAddress()) child.setAddress(address);
        return child;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && name.equals(person.name) && surname.equals(person.surname) && address.equals(person.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age, address);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        if (!hasAge() && !hasAddress()) return "Person: " + name + " " + surname;

        if (hasAge() && !hasAddress()) return "Person: " + name + " " + surname +
                ", ВОЗРАСТ: " + age;

        if (!hasAge() && hasAddress()) return "Person: " + name + " " + surname +
                ", АДРЕС: " + address;

        return "Person: " + name + " " + surname +
                ", ВОЗРАСТ: " + age +
                ", АДРЕС: " + address;
    }
}
