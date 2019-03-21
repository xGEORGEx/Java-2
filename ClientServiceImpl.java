package ru.geekbrains.J2L7;

import ru.geekbrains.J2L7.models.Client;

import java.io.IOException;
import java.util.HashMap;

public class ClientServiceImpl implements ClientService{
    private final Client client;
    private final MessageService messageService;
    private final ClientStorage clientStorage;

    public ClientServiceImpl(Client client, MessageService messageService, ClientStorage clientStorage){
        this.client = client;
        this.messageService = messageService;
        this.clientStorage = clientStorage;
    }

    @Override
    public void processMessage() {
        try {
            while (true) {
                String message = client.getIs().readUTF();
                messageService.sendMessage(client.getLogin(),message);
            }
        } catch (IOException io) {
            clientStorage.removeClient(client);
            io.printStackTrace();
        }
    }
}
