package ru.geekbrains.J2L7.models;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private final String login;
    private final DataInputStream is;
    private final DataOutputStream os;

    public Client(String login, DataInputStream is, DataOutputStream os) throws IOException {
        this.login = login;
        this.is = is;
        this.os = os;
    }

    public String getLogin() {
        return login;
    }

    public DataInputStream getIs() {
        return is;
    }

    public DataOutputStream getOs() {
        return os;
    }

    @Override
    public String toString() {
        return "Client{" +
                "login='" + login + '\'' +
                '}';
    }
}
