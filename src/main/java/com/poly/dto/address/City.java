package com.poly.dto.address;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Data
public class City {
    @JsonProperty("name")
    private String name;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("type")
    private String type;

    @JsonProperty("name_with_type")
    private String nameWithType;

    @JsonProperty("code")
    private String code;
}
