package com.westartrek.graphql.test.api.controller;

import com.westartrek.graphql.test.api.entity.Person;
import com.westartrek.graphql.test.api.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @PostMapping("/addpersons")
    public String savePersons(@RequestBody  List<Person> persons) {
        return personService.addPersons(persons);
    }

    @GetMapping("findpersons")
    public List<Person> findAll() {
        return personService.findPersons();
    }

    @PostMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestBody String query) {
        return ResponseEntity.ok(personService.getGraphQlQuery(query));
    }

    @PostMapping("/getPersonByEmail")
    public ResponseEntity<?> getByEmail(@RequestBody String query) {
        return ResponseEntity.ok(personService.getGraphQlQuery(query));
    }
}
