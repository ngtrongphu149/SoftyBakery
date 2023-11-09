package com.poly.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class JsonReaderUtil {
    private final ObjectMapper objectMapper;

    public JsonReaderUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> List<T> read(String filePath, Class<T> valueType) {
        try {
            String stringGetFromFile = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
            return objectMapper.readValue(stringGetFromFile, new TypeReference<List<T>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> String write(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    
}
