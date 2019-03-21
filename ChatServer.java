package ru.geekbrains.J2L7;

import ru.geekbrains.J2L7.models.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    private static ClientStorage clientStorage = new ClientStorage();
    private static MessageService messageService = new MessageService(clientStorage);

    public static void main(String[] args) throws IOException {
        try (ServerSocket ss = new ServerSocket(8080)) {
            System.out.println("server started");
            while (true) {
                Socket socket = ss.accept();
                DataInputStream is = new DataInputStream(socket.getInputStream());
                DataOutputStream os = new DataOutputStream(socket.getOutputStream());

                Client client = new Client(is.readUTF(), is, os);
                System.out.println("client connected::" + client + "::" + socket);
                clientStorage.addClient(client);
                messageService.sendMessage(client.getLogin() + " зашёл в чат!" + "\n");
                new Thread(()-> new ClientServiceImpl(client, messageService, clientStorage).processMessage()).start();
            }
        }
    }
}
