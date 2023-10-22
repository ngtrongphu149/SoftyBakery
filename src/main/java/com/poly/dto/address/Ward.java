package com.poly.dto.address;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Data
public class Ward {
    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("name_with_type")
    private String nameWithType;

    @JsonProperty("path")
    private String path;

    @JsonProperty("path_with_type")
    private String pathWithType;

    @JsonProperty("code")
    private String code;

    @JsonProperty("parent_code")
    private String parentCode;
}