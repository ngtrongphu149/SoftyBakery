package com.poly.controllers;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.poly.dao.AccountDAO;
import com.poly.dao.CategoryDAO;
import com.poly.dao.ProductDAO;
import com.poly.dao.ProductImageDAO;
import com.poly.entities.AccountDTO;
import com.poly.entities.CategoryDTO;
import com.poly.entities.ProductDTO;
import com.poly.entities.ProductImageDTO;
import com.poly.models.Account;
import com.poly.models.Category;
import com.poly.models.Product;
import com.poly.models.ProductImage;

@RestController
public class main {
    @Autowired
    CategoryDAO cDAO;
    @Autowired ProductDAO pDAO;
    @Autowired ProductImageDAO piDAO;
    @Autowired AccountDAO aDAO;
    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/meo")
    public String home() throws IOException{
        try {
            String fileName = "Accounts.json";
            String jsonInfo = getJsonInfo(fileName);
            
            
            List<AccountDTO> listDTOs = objectMapper.readValue(jsonInfo, objectMapper.getTypeFactory().constructCollectionType(List.class, AccountDTO.class));
            for(AccountDTO dto : listDTOs) {
                Account a = new Account();
                a.setUsername(dto.getUsername());
                a.setPassword(dto.getPassword());
                a.setEmail(dto.getEmail());
                a.setFullName(dto.getFullName());
                a.setAddress(dto.getAddress());
                a.setAddressDetail(dto.getAddressDetail());
                a.setPhoneNumber(dto.getPhoneNumber());
                a.setPhoto(dto.getPhoto());
                a.setBanned(false);

                aDAO.save(a);
            }
            return jsonInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getJsonInfo(String fileName) {
        String path = "src\\main\\resources\\static\\store\\" + fileName;
        try {
            byte[] textFromFile = Files.readAllBytes(Paths.get(path));
            return new String(textFromFile, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}