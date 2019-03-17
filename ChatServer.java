package ru.geekbrains.J2L6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket ss = new ServerSocket(8080)) {
            System.out.println("server started");
            Socket socket = ss.accept();
            System.out.println("client connected" + socket);

            //Создаём поток для отправки сообщений от сервера к клиенту
            Thread thread = new Thread(() ->{
                try (DataOutputStream os = new DataOutputStream(socket.getOutputStream());
                     Scanner scanner = new Scanner(System.in)) {
                    while (true){
                        os.writeUTF("Server: " + scanner.nextLine());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            //Делаем поток демоном и запускаем его
            thread.setDaemon(true);
            thread.start();

            //В основном потоке будем принимать сообщения от клиента
            try (DataInputStream is = new DataInputStream(socket.getInputStream())) {
                while (true){
                    String messageClient = is.readUTF();
                    System.out.println("Client: " + messageClient);
                }
            }
        }
    }
}
