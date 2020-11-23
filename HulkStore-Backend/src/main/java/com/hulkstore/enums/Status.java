package com.hulkstore.enums;

public enum Status {

    Ordered("Ordered"),Cancelled("Cancelled"),Delivered("Delivered");

    private String status;

    Status(String status){
        this.status = status;
    }

}
