import java.io.File;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Logger logger = Logger.getInstance();

    public static void main(String[] args) {
        logger.log("Запускаем программу");

        logger.log("Просим пользователя ввести входные данные для списка");
        int size = requestValue("Введите размер списка: ");
        int maxValue = requestValue("Введите верхнюю границу для значений: ");

        List<Integer> list = createAndFillList(size, maxValue);

        logger.log("Просим пользователя ввести входные данные для фильтрации");
        int filterValue = requestFilterValue(maxValue);

        List<Integer> filteredList = new Filter(filterValue).filterOut(list);

        logger.log("Выводим результат на экран");
        System.out.print("Отфильтрованный список: ");
        filteredList.forEach(i -> System.out.print(i + " "));
        System.out.println();

        logger.log("Завершаем программу");
    }

    public static int requestValue(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                int size = Integer.parseInt(scanner.nextLine());
                if (size <= 0) throw new NumberFormatException();
                return size;
            } catch (NumberFormatException e) {
                System.out.println("Значение должно быть положительным числом");
                logger.log("Пользователь ввел неверные данные");
            }

        }
    }

    private static int requestFilterValue(int value) {
        while (true) {
            System.out.print("Введите порог для фильтра: ");
            try {
                int filterValue = Integer.parseInt(scanner.nextLine());
                if (filterValue < 0 || filterValue >= value) throw new NumberFormatException();
                return filterValue;
            } catch (NumberFormatException e) {
                System.out.println("Значение должно быть положительным и должно быть меньше верхней граници для значений");
                logger.log("Пользователь ввел неверные данные");
            }
        }
    }

    private static List<Integer> createAndFillList(int size, int maxValue) {
        logger.log("Создаём список");
        List<Integer> result = new ArrayList<>(size);
        Random random = new Random();
        logger.log("Наполняем список");
        for (int i = 0; i < size; i++) {
            result.add(random.nextInt(maxValue));
        }
        return result;
    }
}
