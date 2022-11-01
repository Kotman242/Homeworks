import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        List<GameProgress> list = new ArrayList<>();
        Collections.addAll(list, new GameProgress(11, 10, 9, 7.5),
                new GameProgress(77, 55, 37, 9.9),
                new GameProgress(1, 1, 1, 1.0));
        List<String> pathToFiles = saveGame("D:/Games/savegames", list);
        zipFiles("D:/Games/savegames", pathToFiles);
        deletFiles(pathToFiles);
    }

    public static List<String> saveGame(String path, List<GameProgress> list) {
        List<String> pathToZip = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            GameProgress g = list.get(i);
            if (i == 0) {
                stringBuilder.append(path).append("/save").append(".txt");
            } else {
                stringBuilder.append(path).append("/save").append(i).append(".txt");
            }
            try (FileOutputStream fos = new FileOutputStream(String.valueOf(stringBuilder));
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(g);
            } catch (IOException e) {
                e.getMessage();
            }
            pathToZip.add(String.valueOf(stringBuilder));
            stringBuilder.setLength(0);
        }
        return pathToZip;
    }

    public static void zipFiles(String pathToDirectory, List<String> pathToFiles) {
        int count;
        StringBuilder sb = new StringBuilder().append(pathToDirectory).append("/zip_out.zip");
        try (ZipOutputStream zops = new ZipOutputStream(new FileOutputStream(String.valueOf(sb)))) {
            pathToFiles.stream().forEach(x -> {
                try {
                    FileInputStream fips = new FileInputStream(x);
                    sb.setLength(0);
                    sb.append(x).replace(0, 19, "zip_");
                    ZipEntry entry = new ZipEntry(String.valueOf(sb));
                    zops.putNextEntry(entry);
                    byte[] buffer = new byte[fips.available()];
                    fips.read(buffer);
                    zops.write(buffer);
                    zops.closeEntry();
                    fips.close();
                } catch (IOException e) {
                    e.getMessage();
                }
            });
        } catch (IOException e) {
            e.getMessage();
        }

    }

    private static void deletFiles(List<String> path) {
        for (int i = 0; i < path.size(); i++) {
            File file = new File(path.get(i));
            if (file.delete()) {
                System.out.println("файл удален");
            }
        }
    }
}
