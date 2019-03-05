package ru.geekbrains.J2L3;

import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBook {
    private HashMap<String, ArrayList<String>> phoneBook = new HashMap();


    public void add (String name, String phone){
        ArrayList<String> phoneList = new ArrayList<>();
        if (get(name) != null){
            phoneList = phoneBook.get(name);
        }
        phoneList.add(phone);
        phoneBook.put(name, phoneList);
    }

    public ArrayList get (String name){
        return phoneBook.get(name);
    }
}
