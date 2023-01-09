import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String[] texts = new String[25];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("aab", 30_000);
        }

        long startTs = System.currentTimeMillis(); // start time

        List<FutureTask<String>> threads = new ArrayList<>();

        for (int q = 0; q < texts.length; q++) {
            int finalQ = q;

            threads.add(new FutureTask<>(() -> {
                String text = texts[finalQ];
                int maxSize = 0;
                for (int i = 0; i < text.length(); i++) {
                    for (int j = 0; j < text.length(); j++) {
                        if (i >= j) {
                            continue;
                        }
                        boolean bFound = false;
                        for (int k = i; k < j; k++) {
                            if (text.charAt(k) == 'b') {
                                bFound = true;
                                break;
                            }
                        }
                        if (!bFound && maxSize < j - i) {
                            maxSize = j - i;
                        }
                    }
                }
                return text.substring(0, 100) + " -> " + maxSize;
            }));
            new Thread(threads.get(q)).start();
        }

        int maxResult = 0;
        String result = null;
        String value;
        int resultValue;

        for (FutureTask futureTask : threads) {
            value = (String) futureTask.get();
            resultValue = Integer.parseInt(value.substring(value.length() - 2));
            if (resultValue > maxResult) {
                maxResult = resultValue;
                result = value;
            }
        }

        System.out.println(result);

        long endTs = System.currentTimeMillis(); // end time

        System.out.println("Time: " + (endTs - startTs) + "ms");
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}