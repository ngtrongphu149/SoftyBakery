package com.poly.dto.address;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Data
public class District {
    @JsonProperty("name")
    private String name;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("type")
    private String type;

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
