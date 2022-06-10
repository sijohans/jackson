package se.omegapoint.jackson.shapes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Circle extends Shape {

    private final int radius;

    public Circle(@JsonProperty(value = "radius", required = true) int radius) {
        super(ShapeType.CIRCLE);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }
}
