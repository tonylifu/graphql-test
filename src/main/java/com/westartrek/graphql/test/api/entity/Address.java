package com.westartrek.graphql.test.api.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String number;
    private String street;
    private String city;
    private String postCode;
    private String state;
    private String country;
}
