import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PhoneBookTests {
    static PhoneBook phoneBook;

    @BeforeEach
    public void phoneBookClearing() {
        phoneBook = new PhoneBook();
    }

    @Test
    public void searchOfContactByPhone() {
        Contact contact = new Contact("Артем", "446161161");
        phoneBook.addGroup("Друзья");
        phoneBook.addContactToGroup(contact, "Друзья");
        assertThat(contact, equalTo(phoneBook.searchOfContactByPhone("446161161")));
    }

    @Test
    public void searchOfContactsByNameOfGroups() {
        Contact contact = new Contact("Артем", "446161161");
        phoneBook.addGroup("Друзья");
        phoneBook.addContactToGroup(contact, "Друзья");
        List<Contact> list = new ArrayList<>();
        list.add(contact);
        assertThat(list, equalTo(phoneBook.searchOfContactsByNameOfGroups("Друзья")));
    }

    @AfterEach
    public void addPhoneBook() {
        phoneBook = new PhoneBook();
        System.out.println("Телефонная книга создаю. Тест начинается");
    }
}
