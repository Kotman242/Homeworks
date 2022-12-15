import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static Logger logger;
    private int idOfMsg = 0;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyy HH:mm:ss");

    private Logger() {
    }

    public static Logger getInstance() {
        if (logger == null) logger = new Logger();
        return logger;
    }

    public void log(String msg) {
        LocalDateTime time = LocalDateTime.now();
        System.out.println("[" + time.format(formatter) + " " + idOfMsg + "]" + " " + msg);
        idOfMsg++;
    }
}
