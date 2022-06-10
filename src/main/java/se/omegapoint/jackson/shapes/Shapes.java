package se.omegapoint.jackson.shapes;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(using = ShapesDeserializer.class)
public class Shapes {

    private final List<Shape> shapes;

    public Shapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

}
