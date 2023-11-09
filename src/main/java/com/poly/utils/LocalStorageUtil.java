package com.poly.utils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.prefs.Preferences;

@Service
public class LocalStorageUtil {

    private static final String PREFIX = "softy-bakery";

    private Preferences preferences;

    public LocalStorageUtil() {
        this.preferences = Preferences.userRoot().node(PREFIX);
    }

    public void saveCartForUser(String username, String cartJson) {
        preferences.put(username + "_cart", cartJson);
    }

    public String getCartForUser(String username) {
        return preferences.get(username + "_cart", null);
    }
    public void save(String key, String value) {
        preferences.put(key, value);
    }

    public String load(String key) {
        return preferences.get(key, null);
    }
    public JsonNode loadJsonFromLocalStorage(String key) {
        String jsonString = load(key); // Load JSON string from local storage using the load method
        if (jsonString != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.readTree(jsonString);
            } catch (JsonProcessingException e) {
            }
        }
        return null;
    }
}
