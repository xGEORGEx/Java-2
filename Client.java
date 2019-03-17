package ru.geekbrains.J2L6;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Client extends JFrame {

    private Socket socket;
    private Scanner scanner;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;

    public Client() {
        initConnection();
        iniConsolScaner();
    }


    private void initConnection() {
        try {
            socket = new Socket("localhost", 8080);
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());
            System.out.println("connection initialized");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processMessage() {
        //Создаём поток для приёма сообщений от сервера
        Thread thread = new Thread(() ->{
            while (true){
                try {
                    System.out.println(inputStream.readUTF());
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
        //Делаем поток демоном и запускаем его
        thread.setDaemon(true);
        thread.start();

        //В основном потоке будем отправлять сообщения на сервер
        while (true) {
            String message = scanner.nextLine();
            sendMessage(message);
        }

    }

    private void sendMessage(String message) {
        try {
            outputStream.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void iniConsolScaner() {
        scanner = new Scanner(System.in);
        System.out.println("Consol Scaner initialized ");
    }

    public static void main(String[] args) {
        //Создаём новый объект класса клиент и вызываем метод для "переписки" с сервером
        Client client = new Client();
        client.processMessage();
    }
}
