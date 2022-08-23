package se.omegapoint.jackson.person;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Person {

    private final int age;
    private final String name;

    public Person(@JsonProperty(value = "age", required = true) int age,
                  @JsonProperty(value = "name", required = true) String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }

    public static Person fromString(String jsonRep) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.ACCEPT_FLOAT_AS_INT)
                .enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
                .enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY)
                .disable(DeserializationFeature.WRAP_EXCEPTIONS);

        return mapper.readValue(jsonRep, Person.class);
    }

}
