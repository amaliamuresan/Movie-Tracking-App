package com.server.restservice.operation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Hashtable;

public class JsonOperation {
    public static JsonNode createJson(String key, String value)
    {
       String jsonBody = "{\"" + key + "\":\"" + value + "\"}";
       return createJson(jsonBody);
    }

    public static JsonNode createJson(Hashtable<String, String> keyValue)
    {
        String jsonBody = "{";
        for(String key : keyValue.keySet())
        {
            String value = keyValue.get(key);
            jsonBody += "\"" + key + "\":\"" + value + "\"";
            jsonBody += ",";
        }
        jsonBody = jsonBody.replaceAll(".$","");
        jsonBody += "}";

        return createJson(jsonBody);
    }

    public static JsonNode createJson(String jsonBody)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonObj = mapper.readTree(jsonBody);
            return jsonObj;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
