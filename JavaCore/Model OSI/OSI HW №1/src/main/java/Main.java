import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new Server());
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String host = "127.0.0.1";
        int port = 61136;

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println(bufferedReader.readLine());
            System.out.print("Клиент: Сервер ждет Ваше имя - ");
            printWriter.println(scanner.readLine());
            System.out.println(bufferedReader.readLine());
            thread.interrupt();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}




