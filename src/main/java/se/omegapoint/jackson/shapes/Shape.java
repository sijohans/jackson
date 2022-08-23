package se.omegapoint.jackson.shapes;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type") @JsonSubTypes({
        @JsonSubTypes.Type(value = Triangle.class, name = "0"),
        @JsonSubTypes.Type(value = Circle.class, name = "1"),
        @JsonSubTypes.Type(value = Square.class, name = "3"),
        @JsonSubTypes.Type(value = Rectangle.class, name = "2")
})
public abstract class Shape {

    private final ShapeType type;

    public Shape(ShapeType type) {
        this.type = type;
    }

    public ShapeType getType() {
        return type;
    }

    public static Shape fromString(String jsonRep) throws JsonProcessingException {
        /* TODO */
        return new ObjectMapper().readValue(jsonRep, Shape.class);
    }

}
