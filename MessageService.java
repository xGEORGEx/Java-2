package ru.geekbrains.J2L7;

import ru.geekbrains.J2L7.models.Client;

import java.io.IOException;
import java.util.StringTokenizer;

public class MessageService {
    private ClientStorage clientStorage;

    public MessageService(ClientStorage clientStorage) {
        this.clientStorage = clientStorage;
    }

    public synchronized void sendMessage(String login, String message){
        if (message.startsWith("/w ")){
            int messageIndex = message.indexOf(" ", 3);
            String loginName = message.substring(3, messageIndex);
            if (clientStorage.getClients().containsKey(loginName)){
                String personalMessage = message.substring(messageIndex + 1);
                try {
                    clientStorage.getClients().get(loginName).getOs().writeUTF(formatMessage(login, personalMessage));
                    clientStorage.getClients().get(login).getOs().writeUTF(formatMessage(login, personalMessage));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            clientStorage.getClients().forEach((s, client) -> {
                try {
                    client.getOs().writeUTF(formatMessage(login, message));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void sendMessage(String message){
        clientStorage.getClients().forEach((s, client) -> {
            try {
                client.getOs().writeUTF(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    //Метод который обединяет логин и сообщение
    private String formatMessage (String login, String message){
       return login + "::" + message + "\n";
    }
}
