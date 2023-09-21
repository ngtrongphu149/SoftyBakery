package com.poly.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class City {
    private String name;
    private String slug;
    private String type;
    private String name_with_type;
    private String code;
}
