package com.westartrek.graphql.test.api.service;

import com.westartrek.graphql.test.api.entity.Person;
import com.westartrek.graphql.test.api.repository.PersonRepository;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceQL implements PersonService {
    private final PersonRepository personRepository;
    @Value("classpath:graphql/person.graphqls")
    private Resource schemaResource;
    private GraphQL graphQL;

    @PostConstruct
    public void loadSchema() throws IOException {
        File schemaFile = schemaResource.getFile();
        TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildWiring() {
        DataFetcher<List<Person>> fetcher1 = data -> personRepository.findAll();

        DataFetcher<Person> fetcher2 = data -> personRepository.findByEmail(data.getArgument("email"));

        return RuntimeWiring.newRuntimeWiring().type("Query", typeWriting ->
                typeWriting.dataFetcher("getAll", fetcher1)
                .dataFetcher("findOne", fetcher2))
                .build();
    }

    @Override
    public String addPersons(List<Person> persons) {
        List<Person> savedPersons = personRepository.saveAll(persons);
        return String.format("%s number of persons inserted", String.valueOf(savedPersons.size()));
    }

    @Override
    public List<Person> findPersons() {
        return personRepository.findAll();
    }

    @Override
    public Object getGraphQlQuery(String query) {
        return graphQL.execute(query);
    }
}
