package com.poly.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

@Service
public class JsonReaderUtil {
    private final ObjectMapper objectMapper;

    public JsonReaderUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> List<T> read(String filePath, Class<T> valueType) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            return objectMapper.readValue(bufferedReader, new TypeReference<List<T>>() {});
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
