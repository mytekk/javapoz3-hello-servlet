package com.sda.chat;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by RENT on 2017-06-26.
 *
 * klasa przechowująca zbior uzytkownikow chatu
 */
public class ChatUsersStorage {

    private Map<String, ChatConnectionFacade> users; //mapa zawierająca pary: "nickname - (socketIn + socketOut + ewentualnie nick)"

    public ChatUsersStorage() {

        users = new HashMap<>();
    }

    public boolean add(String nickname, ChatConnectionFacade user) {
        boolean flag = false;
        if (!users.containsKey(nickname))  {
            users.put(nickname, user);
            flag = true;
        }
        return flag;
    }

    //nasza metoda get zwraca "mapową" metodę get, która zwraca obiekt klasy ChatConnectionFacade
    //czyli obiekt zawierajacy (socketIn + socketOut + ewentualnie nick)
    public ChatConnectionFacade get(String nickname) {

        return users.get(nickname);
    }
}
