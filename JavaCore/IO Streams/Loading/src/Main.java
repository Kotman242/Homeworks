import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {
        String parentPath = "D:/Games/savegames";
        openZipAndDelete(parentPath);
        deserializationAndPrintFiles(parentPath);
    }

    public static void openZipAndDelete(String parentPath) {
        File parent = new File(parentPath);
        if (!parent.isDirectory()) {
            System.out.println("Не указан путь к папке");
        }
        List<File> files = Arrays.asList(parent.listFiles());
        files.stream().forEach(x -> {
            try (ZipInputStream zips = new ZipInputStream(new FileInputStream(x.getPath()))) {
                ZipEntry entry;
                String name;
                while ((entry = zips.getNextEntry()) != null) {
                    name = entry.getName();
                    FileOutputStream fops = new FileOutputStream(x.getParent() + "/" + name);
                    for (int i = zips.read(); i != -1; i = zips.read()) {
                        fops.write(i);
                    }
                    fops.flush();
                    zips.closeEntry();
                    fops.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (x.delete()) {
                System.out.println("Файл: " + x.getName() + " удален");
            }
        });
    }

    public static void deserializationAndPrintFiles(String parentPath) {
        File parent = new File(parentPath);
        if (!parent.isDirectory()) {
            System.out.println("Не указан путь к папке");
        } else {
            List<File> files = Arrays.asList(parent.listFiles());
            files.stream().forEach(x -> {
                GameProgress gameProgress = null;
                try (FileInputStream fips = new FileInputStream(x);
                     ObjectInputStream oips = new ObjectInputStream(fips)) {
                    gameProgress = (GameProgress) oips.readObject();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                System.out.println(gameProgress);
            });
        }
    }
}