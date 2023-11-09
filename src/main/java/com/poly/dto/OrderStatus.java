package com.poly.dto;

public enum OrderStatus {
    DANG_CHO("Đang chờ"),
    DA_GIAO("Đã giao"),
    DA_HUY("Đã hủy");
    
    private String value;
    
    OrderStatus(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}
