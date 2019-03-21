package ru.geekbrains.J2L7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp extends JFrame {
    private Socket socket;
    private JTextArea outputTextArea;
    private JTextField inputTextField;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;

    public ClientApp() {
        iniGui();
    }


    private void initConnection() {
        try {
            socket = new Socket("localhost", 8080);
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());
            System.out.println("connection initialized");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("connection fail");
        }
    }

    private void initReceiver() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    String echoMessage = inputStream.readUTF();
                    outputTextArea.append(echoMessage);

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("receiver fail");
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println("receiver started");
    }

    private void processMessage() {
        if (!inputTextField.getText().equals("")) {
            String message = inputTextField.getText();
            inputTextField.setText("");
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


    private void iniGui() {
        outputTextArea = new JTextArea();
        inputTextField = new JTextField();

        setBounds(500, 200, 400, 400);
        setTitle("Client App");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel textPanel = createTextPanel();
        JPanel buttonPanel = createButtonPanel();
        JPanel authPanel = createAuthPanel(textPanel, buttonPanel);

        add(textPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(authPanel, BorderLayout.NORTH);

        setVisible(true);

        System.out.println("gui initialized ");
    }

    private JPanel createTextPanel() {
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());

        textPanel.add(outputTextArea, BorderLayout.CENTER);
        textPanel.add(new JScrollPane(outputTextArea));
        textPanel.setVisible(false);

        outputTextArea.setLineWrap(true);
        outputTextArea.setEditable(false);     //чтобы нельзя было печатать текст в поле
        return textPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        JButton jSend = new JButton("Отправить");

        buttonPanel.add(inputTextField, BorderLayout.CENTER);
        buttonPanel.add(jSend, BorderLayout.EAST);
        buttonPanel.setVisible(false);

        //Создаём "слушателя" для щелчка по кнопке
        jSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Перекидываем текст из нижнего поля в верхнее
                processMessage();
            }
        });

        //Создаём "слушателя" для нажатия на Enter
        inputTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ENTER){
                    //Перекидываем текст из нижнего поля в верхнее
                    processMessage();
                }
            }
        });
        return buttonPanel;
    }

    private JPanel createAuthPanel(JPanel textPanel, JPanel buttonPanel) {
        JPanel authPanel = new JPanel();
        JTextField loginField = new JTextField();
        JLabel loginLabel = new JLabel("Введите ваш никнейм:");
        loginField.addActionListener(e -> processMessage());



        JButton authButton = new JButton("Вход");
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        authButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        authButton.addActionListener(e -> {
            initConnection();
            initReceiver();
            sendMessage(loginField.getText());
            authPanel.setVisible(false);
            buttonPanel.setVisible(true);
            textPanel.setVisible(true);
            setTitle(loginField.getText());
        });

        loginField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ENTER){
                    initConnection();
                    initReceiver();
                    sendMessage(loginField.getText());
                    authPanel.setVisible(false);
                    buttonPanel.setVisible(true);
                    textPanel.setVisible(true);
                    setTitle(loginField.getText());
                }
            }
        });

        authPanel.setLayout(new BoxLayout(authPanel, BoxLayout.PAGE_AXIS));
        authPanel.add(loginLabel);
        authPanel.add(loginField);
        authPanel.add(authButton);
        authPanel.setVisible(true);
        return authPanel;
    }

    public static void main(String[] args) {
        //Создаём новый объект класса клиент и вызываем метод для "переписки" с сервером
        new ClientApp();
        new ClientApp();
        new ClientApp();
    }
}
