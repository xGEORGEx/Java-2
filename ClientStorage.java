package ru.geekbrains.J2L7;

import ru.geekbrains.J2L7.models.Client;

import java.util.HashMap;

public class ClientStorage {
    private static HashMap<String, Client> clientsList = new HashMap <>();

    public void addClient(Client client){
        clientsList.put(client.getLogin(), client);
    }

    public void removeClient(Client client){
        clientsList.remove(client.getLogin());
    }

    public HashMap<String, Client> getClients(){
        return clientsList;
    }
}
