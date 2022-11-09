import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {

    private Map<String, List<Contact>> map = new HashMap<>();
    private List<String> listOfGroups = new ArrayList<>();

    public boolean addGroup(String nameOfGroup) {
        if (map.containsKey(nameOfGroup)) {
            return false;
        }
        map.put(nameOfGroup, new ArrayList<>());
        listOfGroups.add(nameOfGroup);
        return true;
    }

    public Contact newContact(String name, String phone) {
        return new Contact(name, phone);
    }

    public int addContactToGroup(Contact contact, String... nameOfGroups) {
        int count = 0;
        Map<Contact, String> map1 = new HashMap<>();
        for (String group : nameOfGroups) {
            if (!listOfGroups.contains(group)) {
                System.out.printf("Группа %s не найдена\n", group);
            } else {
                map.get(group).add(contact);
                map1.put(contact, group);
                System.out.printf("Контакт %s добавлен в группу %s\n", contact.getName(), group);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Контакты не были добавлены");
        } else if (count == nameOfGroups.length - 1) {
            count = -1;
        } else {
            System.out.println("Все контакты добавлены");
            count = 1;
        }
        return count;
    }

    public List<Contact> searchOfContactsByNameOfGroups(String nameOfGroup) {
        List<Contact> list = map.get(nameOfGroup);
        return list;
    }

    public Contact searchOfContactByPhone(String phone) {
        for (List<Contact> list : map.values()) {
            for (Contact contact : list) {
                if (contact.getPhone().equals(phone)) {
                    return contact;
                }
            }
        }
        return null;
    }
}
