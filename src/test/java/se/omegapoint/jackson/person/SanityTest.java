package se.omegapoint.jackson.person;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

public class SanityTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testSanity() throws JsonProcessingException {
        String jsonRep = "{\"age\": 10, \"name\": \"Alice\"}";
        Person person = mapper.readValue(jsonRep, Person.class);
        Assert.assertNotNull(person);
        Assert.assertEquals(10, person.getAge());
        Assert.assertEquals("Alice", person.getName());
    }

    @Test(expected = JsonProcessingException.class)
    public void testDoNotAllowMissingAge() throws JsonProcessingException {
        String jsonRep = "{\"name\": \"Bob\"}";
        mapper.readValue(jsonRep, Person.class);
    }

    @Test(expected = JsonProcessingException.class)
    public void testDoNotAllowMissingName() throws JsonProcessingException {
        String jsonRep = "{\"age\": 15}";
        mapper.readValue(jsonRep, Person.class);
    }

}
