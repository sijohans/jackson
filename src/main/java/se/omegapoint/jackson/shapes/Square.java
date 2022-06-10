package se.omegapoint.jackson.shapes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Square extends Shape {

    private final int size;

    public Square(@JsonProperty(value = "size", required = true) int size) {
        super(ShapeType.SQUARE);
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
