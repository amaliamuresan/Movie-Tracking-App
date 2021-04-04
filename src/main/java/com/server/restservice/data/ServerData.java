package com.server.restservice.data;

import com.server.restservice.models.User;

import java.util.ArrayList;
import java.util.Hashtable;

public class ServerData {
    private static Hashtable<String, String> UserTokens = new Hashtable<>();

    public static String getToken(String uid) {
        return UserTokens.get(uid);
    }

    public static void addToken(String uid, String token) {
        UserTokens.put(uid,token);
    }

    public static void removeToken(String uid) {
        UserTokens.remove(uid);
    }
}
