import java.util.ArrayList;
import java.util.List;

public class Filter {
    protected int treshold;
    Logger logger = Logger.getInstance();

    public Filter(int treshold) {
        logger.log("Создаем фильтр");
        this.treshold = treshold;
    }

    public List<Integer> filterOut(List<Integer> source) {
        logger.log("Запускаем фильтрацию");
        List<Integer> result = new ArrayList<>();
        for (Integer number : source) {
            if (number < treshold) {
                logger.log("Элемент \"" + number + "\" не проходит");
            } else {
                logger.log("Элемент \"" + number + "\" проходит");
                result.add(number);
            }
        }
        logger.log(String.format("Прошло фильтр %d элемента из %d", result.size(), source.size()));
        return result;
    }
}
