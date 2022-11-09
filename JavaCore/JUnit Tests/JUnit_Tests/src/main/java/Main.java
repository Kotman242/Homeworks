public class Main {
        public static void main(String[] args) {
            PhoneBook book = new PhoneBook();
            book.addGroup("Работа");
            book.addGroup("Семья");
            book.addGroup("Друзья");
            book.addContactToGroup(book.newContact("Олег", "555555555"), "Работа", "Семья");
            book.addContactToGroup(book.newContact("Ольга", "777777777"), "Семья");
            book.addContactToGroup(book.newContact("Иван", "666666666"), "Друзья");
            System.out.println(book.searchOfContactsByNameOfGroups("Семья"));
            System.out.println(book.searchOfContactByPhone("777777777"));
        }
    }

