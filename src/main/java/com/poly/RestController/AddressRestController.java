package com.poly.RestController;


import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.*;
import com.poly.entities.*;
import com.poly.model.*;
import DB.UserUtils;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/address")
public class AddressRestController {
	private final String url = "C:\\Users\\lunba\\SoftyBakery\\src\\main\\resources\\static\\data\\vietnam_location\\";
    private final String cityFilePath = url+"cities.json";
    private final String districtFilePath = url+"districts.json";
    private final String wardFilePath = url+"wards.json";

    @GetMapping("/cities")
    public ResponseEntity<List<City>> cityIndex() {
    	List<City> cityList = readCitiesFromJson(cityFilePath);
        if (cityList != null) {
           
            return ResponseEntity.ok(cityList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{cityCode}/districts")
    public ResponseEntity<List<District>> districtIndex(@PathVariable("cityCode") String cityCode) {
        List<District> districtList = readDistrictsFromJson(districtFilePath);
        if (districtList != null) {
            List<District> filteredDistricts = new ArrayList<>();
            for (District district : districtList) {
                if (district.getParent_code().equals(cityCode)) {
                    filteredDistricts.add(district);
                }
            }
            return ResponseEntity.ok(filteredDistricts);
        } else {
            return null;
        }
    }

    @GetMapping("/{districtCode}/wards")
    public ResponseEntity<List<Ward>> wardIndex(@PathVariable("districtCode") String districtCode) {
        List<Ward> wardList = readWardsFromJson(wardFilePath);
        if (wardList != null) {
            List<Ward> filteredWards = new ArrayList<>();
            for (Ward ward : wardList) {
                if (ward.getParent_code().equals(districtCode)) {
                    filteredWards.add(ward);
                }
            }
            return ResponseEntity.ok(filteredWards);
        } else {
            return null;
        }
    }

    private List<City> readCitiesFromJson(String filePath) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(filePath));
            JSONArray citiesArray = (JSONArray) obj;

            List<City> cityList = new ArrayList<>();

            for (Object city : citiesArray) {
                JSONObject cityObject = (JSONObject) city;
                City c = new City();
                c.setName((String) cityObject.get("name"));
                c.setSlug((String) cityObject.get("slug"));
                c.setType((String) cityObject.get("type"));
                c.setName_with_type((String) cityObject.get("name_with_type"));
                c.setCode((String) cityObject.get("code"));
                cityList.add(c);
            }
            cityList.sort(Comparator.comparing(City::getName));
            return cityList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<District> readDistrictsFromJson(String filePath) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(filePath));
            JSONArray districtsArray = (JSONArray) obj;

            List<District> districtList = new ArrayList<>();

            for (Object district : districtsArray) {
                JSONObject districtObject = (JSONObject) district;
                District d = new District();
                d.setName((String) districtObject.get("name"));
                d.setSlug((String) districtObject.get("slug"));
                d.setType((String) districtObject.get("type"));
                d.setName_with_type((String) districtObject.get("name_with_type"));
                d.setPath((String) districtObject.get("path"));
                d.setPath_with_type((String) districtObject.get("path_with_type"));
                d.setCode((String) districtObject.get("code"));
                d.setParent_code((String) districtObject.get("parent_code"));
                districtList.add(d);
            }
            districtList.sort(Comparator.comparing(District::getName));
            return districtList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Ward> readWardsFromJson(String filePath) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(filePath));
            JSONArray wardsArray = (JSONArray) obj;

            List<Ward> wardList = new ArrayList<>();

            for (Object ward : wardsArray) {
                JSONObject wardObject = (JSONObject) ward;
                Ward w = new Ward();
                w.setName((String) wardObject.get("name"));
                w.setSlug((String) wardObject.get("slug"));
                w.setType((String) wardObject.get("type"));
                w.setName_with_type((String) wardObject.get("name_with_type"));
                w.setPath((String) wardObject.get("path"));
                w.setPath_with_type((String) wardObject.get("path_with_type"));
                w.setCode((String) wardObject.get("code"));
                w.setParent_code((String) wardObject.get("parent_code"));
                wardList.add(w);
            }
            wardList.sort(Comparator.comparing(Ward::getName));
            return wardList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
