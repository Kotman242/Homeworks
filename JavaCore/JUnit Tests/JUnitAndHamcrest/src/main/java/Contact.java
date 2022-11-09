public class Contact {
    private String name;
    private String phone;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Контакт " + name + " номер телефона " + phone;
    }
}
