package se.omegapoint.jackson.shapes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Triangle extends Shape {

    private final int height;
    private final int width;

    public Triangle(@JsonProperty(value = "height", required = true) int height,
                    @JsonProperty(value = "width", required = true) int width) {
        super(ShapeType.TRIANGLE);
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
