import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 1234;
        System.out.println("Server started");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader bufferedReader = new BufferedReader(new
                             InputStreamReader(clientSocket.getInputStream()))) {
                    System.out.printf("Новое подключение, порт №%s \n", clientSocket.getPort());
                    printWriter.println("Сервер: Данный сервер информирует о погоде.");
                    printWriter.println("Сервер: В каком городе вы ходите узнать погоду: " +
                            "Москва введите 1, Санкт-Петербург введите 2");
                    printWriter.println(bufferedReader.readLine().equals("1") ?
                            "Сервер: Всегда солнечно, +15" :
                            "Сервер: Всегда дождь, +10");
                    printWriter.println("Сервер: Введите город в котором хотите узнать погоду: ");
                    printWriter.println(String.format("Сервер: " + bufferedReader.readLine()) + " температура " + (int) ((Math.random() * 30) - 10));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
