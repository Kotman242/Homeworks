import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        String host = "netology.homework";
        int port = 1234;

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader bufferedReader = new BufferedReader(new
                     InputStreamReader(clientSocket.getInputStream()));
             BufferedReader scanner = new BufferedReader(new
                     InputStreamReader(System.in))) {

            System.out.println(bufferedReader.readLine());
            System.out.println(bufferedReader.readLine());
            System.out.print("Введите ответ:  ");
            printWriter.println(scanner.readLine());
            System.out.println(bufferedReader.readLine());
            System.out.print(bufferedReader.readLine());
            printWriter.println(scanner.readLine());
            System.out.println(bufferedReader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
