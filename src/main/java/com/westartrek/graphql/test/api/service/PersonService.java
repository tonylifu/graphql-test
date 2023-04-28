package com.westartrek.graphql.test.api.service;

import com.westartrek.graphql.test.api.entity.Person;

import java.io.IOException;
import java.util.List;

public interface PersonService {
    String addPersons(List<Person> persons);

    List<Person> findPersons();

    Object getAll(String query);
}
