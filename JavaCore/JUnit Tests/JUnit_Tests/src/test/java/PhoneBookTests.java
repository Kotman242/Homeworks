import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneBookTests {
    static PhoneBook phoneBook;

    public static Stream<Arguments> methodSourse() {
        return Stream.of(
                Arguments.of("Семья"),
                Arguments.of("Друзья"),
                Arguments.of("Одногруппники"),
                Arguments.of("Не брать"));
    }

    @BeforeEach
    public void phoneBookClearing() {
        phoneBook = new PhoneBook();
    }

    @MethodSource("methodSourse")
    @ParameterizedTest
    public void addGroupTest(String name) {
        assertTrue(phoneBook.addGroup(name));
    }

    @Test
    public void addGroupTestFalse() {
        phoneBook.addGroup("Друзья");
        String name = "Друзья";
        assertFalse(phoneBook.addGroup(name));
    }

    @Test
    public void addContactToGroupTestAllContacts() {
        phoneBook.addGroup("Друзья");
        phoneBook.addGroup("Семья");
        assertEquals(1, phoneBook.addContactToGroup(new Contact("Олег", "1578965"), "Друзья", "Семья"));
    }

    @Test
    public void addContactToGroupTestOneContact() {
        phoneBook.addGroup("Друзья");
        assertEquals(-1, phoneBook.addContactToGroup(new Contact("Олег", "1578965"), "Друзья", "Семья"));
    }

    @Test
    public void addContactToGroupTestNoBody() {
        phoneBook.addGroup("Семья");
        assertEquals(0, phoneBook.addContactToGroup(new Contact("Олег", "1578965"), "Работа", "Коллеги"));
    }

    @AfterEach
    public void addPhoneBook() {
        phoneBook = new PhoneBook();
        System.out.println("Телефонная книга создаю. Тест начинается");
    }
}
