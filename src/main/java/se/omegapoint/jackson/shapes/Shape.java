package se.omegapoint.jackson.shapes;

public abstract class Shape {

    private final ShapeType type;

    public Shape(ShapeType type) {
        this.type = type;
    }

    public ShapeType getType() {
        return type;
    }

}
