package se.omegapoint.jackson.person;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class StrictTypesTest {

    @Parameters
    public static Collection<String> data() {
        return Arrays.asList(new String[]{
                /* Invalid type for age */
                "{\"age\": 14.32,        \"name\": \"Alice\"}",  // ok by default
                "{\"age\": -1.67,        \"name\": \"Alice\"}", // ok by default
                "{\"age\": \"10\",       \"name\": \"Alice\"}", // ok by default
                "{\"age\": null,         \"name\": \"Alice\"}", // ok by default
                "{\"age\": true,         \"name\": \"Alice\"}", // nok by default
                "{\"age\": false,        \"name\": \"Alice\"}", // nok by default
                "{\"age\": {},           \"name\": \"Alice\"}", // nok by default
                "{\"age\": [],           \"name\": \"Alice\"}", // nok by default
                "{\"age\": {\"age\":10}, \"name\": \"Alice\"}", // nok by default
                "{\"age\": [{\"age\":10}], \"name\": \"Alice\"}", // nok by default
                /* Invalid type for name */
                "{\"age\": 10, \"name\": 13}", //
                "{\"age\": 10, \"name\": -23}", //
                "{\"age\": 10, \"name\": 2.32}", //
                "{\"age\": 10, \"name\": -0.13}", //
                "{\"age\": 10, \"name\": null}", //
                "{\"age\": 10, \"name\": true}", //
                "{\"age\": 10, \"name\": false}", //
                "{\"age\": 10, \"name\": {}}", // nok by default
                "{\"age\": 10, \"name\": []}", // nok by default
                "{\"age\": 10, \"name\": {\"name\": \"Alice\"}}", // nok by default
                "{\"age\": 10, \"name\": [{\"name\": \"Alice\"}}]",  // nok by default
        });
    }


    private final String jsonRep;

    public StrictTypesTest(String jsonRep) {
        this.jsonRep = jsonRep;
    }

    @Test(expected = JsonProcessingException.class)
    public void testThrowsErrorWithWrongTypes() throws JsonProcessingException {
        Person.fromString(jsonRep);
    }

}
