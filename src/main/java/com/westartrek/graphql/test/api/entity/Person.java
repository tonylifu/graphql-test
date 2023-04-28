package com.westartrek.graphql.test.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Person {
    @Id
    private int id;
    private String name;
    private String mobile;
    private String email;
    private String address;
}