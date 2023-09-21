package com.poly.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ward {
    private String name;
    	private String type;
		private String slug;
		private String name_with_type;
		private String path;
		private String path_with_type;
		private String code;
		private String parent_code;
}
