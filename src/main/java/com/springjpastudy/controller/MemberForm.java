package com.springjpastudy.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MemberForm {

    @NotEmpty(message = "이름은 필수임 ")
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
