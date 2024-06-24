package com.harry.micrometer.model;


import lombok.Data;

@Data
public class AccountModel {

    private String number;
    private String name;
    private String address;
    private double balance;

}
