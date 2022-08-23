package se.omegapoint.jackson.shapes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

//@JsonDeserialize(using = ShapesDeserializer.class)
public class Shapes {

    private final List<Shape> shapes;

    public Shapes(@JsonProperty(value = "shapes", required = true) List<Shape> shapes) {
        this.shapes = shapes;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public static Shapes fromString(String jsonRep) throws JsonProcessingException {
        return new ObjectMapper().readValue(jsonRep, Shapes.class);
    }

}
