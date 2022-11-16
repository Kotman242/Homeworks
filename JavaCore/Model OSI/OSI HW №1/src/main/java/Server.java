import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    private int port = 61136;

    @Override
    public void run() {
        System.out.println("Сервер - Server started");
        try {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                while (true) {
                    try (Socket clientSocket = serverSocket.accept();
                         PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                        System.out.printf("Установлено новое подключение от порта %d\n", clientSocket.getPort());
                        Thread.sleep(1000);
                        printWriter.println("Сервер: Пожалуйста, введите Ваше имя ");
                        Thread.sleep(1000);
                        String name = bufferedReader.readLine();
                        Thread.sleep(1000);
                        printWriter.println(String.format("Сервер: Здравствуйте %s Вы подключены, Ваш порт №%d \n", name, clientSocket.getPort()));
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("Сервер: Сервер отключен");
                        return;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}






