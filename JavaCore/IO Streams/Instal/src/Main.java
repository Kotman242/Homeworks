import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;


public class Main {
    public static void main(String[] args) {
        StringBuilder log = new StringBuilder();
        HashMap<String, String[]> directory = new HashMap<>();
        directory.put("D:/Games/src", new String[]{"main", "test" });
        directory.put("D:/Games", new String[]{"src", "res", "savegames", "temp" });
        directory.put("D:/Games/res", new String[]{"drawables", "vectors", "icons" });
        HashMap<String, String[]> files = new HashMap<>();
        files.put("D:/Games/src/main", new String[]{"Main.java", "Utils.java" });
        files.put("D:/Games/temp", new String[]{"temp.txt" });
        addDirectory(directory, log);
        addFiles(files, log);
        sendLod("D:/Games/temp/temp.txt", log);
        System.out.println(log);


    }

    public static void addDirectory(HashMap<String, String[]> map, StringBuilder log) {
        LocalDateTime localDateTime = LocalDateTime.now();
        StringBuilder path = new StringBuilder();
        map.keySet().stream().sorted().forEach(x -> {
            if (!new File(x).exists()) {
                boolean result = new File(x).mkdir();
                log.append(localDateTime).append(" --- Создание" + x + " каталога. Результат --- " + result + "\n");
            }
            for (int i = 0; i < map.get(x).length; i++) {
                path.append(x + "/").append(map.get(x)[i]);
                boolean result = new File(String.valueOf(path)).mkdir();
                log.append(localDateTime).append(" --- Создание " + path + " каталога. Результат --- " + result + "\n");
                path.setLength(0);
            }
        });
    }

    public static void addFiles(HashMap<String, String[]> mapOfFiles, StringBuilder log) {
        LocalDateTime localDateTime = LocalDateTime.now();
        StringBuilder path = new StringBuilder();
        mapOfFiles.keySet().stream().forEach(x -> {
            for (int i = 0; i < mapOfFiles.get(x).length; i++) {
                //path.append(x).append("//").append(mapOfFiles.get(x)[i]);
                File file = new File(x, mapOfFiles.get(x)[i]);

                try {
                    if (file.createNewFile()) {
                        log.append(localDateTime).append(" --- Создание файла " + mapOfFiles.get(x)[i] + ". Файл был создан" + "\n");
                    }
                } catch (IOException e) {
                    log.append(localDateTime).append(" --- Создание файла " + mapOfFiles.get(x)[i] + ". Файл не был создан" + "\n");
                }
            }
        });
    }

    public static void sendLod(String path, StringBuilder log) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            byte[] bytes = log.toString().getBytes();
            fos.write(bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}