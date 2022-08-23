package se.omegapoint.jackson.person;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class PersonFuzzer {

    @SuppressWarnings("unused")
    public static void fuzzerTestOneInput(com.code_intelligence.jazzer.api.FuzzedDataProvider data) {
        parse(data.consumeRemainingAsString());
    }

    public static void parse(String input) {
        try {
            Person person = Person.fromString(input);
            if (!(person != null && person.getName() != null)) {
                throw new NullPointerException("Something was null"); /* Trigger crash */
            }
            System.out.println("Name: " + person.getName());
            System.out.println("Age: " + person.getAge());

            HashMap<String, Object> object = new ObjectMapper().readValue(input, HashMap.class);
            boolean valid = object.size() == 2;
            valid = valid && (object.get("name") instanceof String);
            valid = valid && (object.get("age") instanceof Integer);
            if (!valid) {
                throw new IllegalStateException("Bad types...");
            }
        } catch(JsonProcessingException e) {
            /* Expected */
        }
    }

    public static void main(String[] args) {
        parse("{\"age\": \"10\", \"name\": \"Alice\"}");
    }

}
