package se.omegapoint.jackson.person;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PersonFuzzer {

    public final static ObjectMapper mapper = new ObjectMapper();

    public static void fuzzerTestOneInput(com.code_intelligence.jazzer.api.FuzzedDataProvider data) {
        parse(data.consumeRemainingAsString());
    }

    public static void parse(String input) {
        try {
            Person person = mapper.readValue(input, Person.class);
            if (!(person != null && person.getName() != null)) {
                throw new NullPointerException("Something was null"); /* Trigger crash */
            }
            System.out.println("Name: " + person.getName());
            System.out.println("Age: " + person.getAge());
        } catch(JsonProcessingException e) {
            /* Expected */
        }
    }

    public static void main(String[] args) {
        parse("{\"age\": 10, \"name\": \"Alice\"}");
    }

}
