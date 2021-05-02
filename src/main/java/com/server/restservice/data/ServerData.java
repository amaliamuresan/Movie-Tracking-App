package com.server.restservice.data;

import com.server.restservice.models.User;

import javax.jws.soap.SOAPBinding;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class ServerData {
    private static Hashtable<String, String> UserTokens = new Hashtable<>();
    private static Hashtable<String, Integer> UserStrikes = new Hashtable<>();
    private static Hashtable<String, Instant> UserStrikeTime = new Hashtable<>();
    private static final String tmdbApiKey = "beded07fb1fd90eef57842dca26e86c0";
    private static final String tmdbApiUrl = "https://api.themoviedb.org/3/";
    private static final String tmdbImageUrl = "https://image.tmdb.org/t/p/w500";
    private static Map<String,String> tmdbGenres;
    private static Map<String,String> tmdbInvertedGenres;
    private static final String omdbApiKey = "35e2fd2";

    public static String getToken(String uid) {
        return UserTokens.get(uid);
    }

    public static void addToken(String uid, String token) {
        UserTokens.put(uid,token);
    }

    public static void removeToken(String uid) {
        UserTokens.remove(uid);
    }

    public static String getTmdbApiKey() {
        return tmdbApiKey;
    }

    public static String getTmdbApiUrl() {
        return tmdbApiUrl;
    }

    public static String getTmdbImageUrl() {
        return tmdbImageUrl;
    }

    public static Map<String, String> getTmdbGenres() {
        return tmdbGenres;
    }

    public static void setTmdbGenres(Map<String, String> tmdbGenres) {
        ServerData.tmdbGenres = tmdbGenres;
    }

    public static Map<String, String> getTmdbInvertedGenres() {
        return tmdbInvertedGenres;
    }

    public static void setTmdbInvertedGenres(Map<String, String> tmdbInvertedGenres) {
        ServerData.tmdbInvertedGenres = tmdbInvertedGenres;
    }

    public static String getOmdbApiKey() {
        return omdbApiKey;
    }

    public static void addStrike(String uid) {
        Integer noStrike = UserStrikes.get(uid);
        if(noStrike != null) {
            noStrike += 1;
        }
        else {
            noStrike = 1;
        }
        if(noStrike >= 3) {
            UserStrikeTime.put(uid,Instant.now());
        }
        UserStrikes.put(uid,noStrike);
    }

    public static boolean isBlocked(String uid) {
        Integer noStrike = UserStrikes.get(uid);
        if(noStrike != null && noStrike >= 3) {
            Duration timeElapsed = Duration.between(UserStrikeTime.get(uid),Instant.now());
            if(timeElapsed.toMillis() > 5000) {
                resetStrikes(uid);
                return false;
            }
            return true;
        }
        else {
            return false;
        }
    }

    public static void resetStrikes(String uid) {
        if(UserStrikes.get(uid) != null) {
            UserStrikes.remove(uid);
        }
    }

}
