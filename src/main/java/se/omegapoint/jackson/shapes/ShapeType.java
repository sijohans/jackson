package se.omegapoint.jackson.shapes;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ShapeType {
    TRIANGLE(0),
    CIRCLE(1),
    RECTANGLE(2),
    SQUARE(3);

    private final int type;

    ShapeType(int type) {
        this.type = type;
    }

    @JsonValue
    public int getType() {
        return type;
    }
}
