# Shapes painting service
Assume we are launching service that paint shapes. We can handle triangles, circles, rectangles and squares. The shapes will arrive in an array and the shapes must be painted in the order they arrive. Someone decided that:
* Measurements (width, height, radius etc) are sent as integer
* The type of shape is also sent as an integer

Someone also likes polymorphism and decided that in the Java implementation there must be:
* A base type (Shape) that has one function that returns the type of the shape which is an enum:
```java
public enum ShapeType {
    TRIANGLE,
    CIRCLE,
    RECTANGLE,
    SQUARE;
}

public abstract class Shape {
    private final ShapeType type;
    public Shape(ShapeType type) {
        this.type = type;
    }
    public ShapeType getType() {
        return type;
    }
}
```
* The Object that represent the request must fullfill/implement:
```java
public interface Shapes {
    List<Shape> getShapes();
}
```

## Discussion
* What is a reasonable format of the request?

## Shapes (CDDL)
```
Triangle = {
    type: 0,
    height: int,
    width: int
}

Circle = {
    type: 1,
    radius: int
}

Rectangle = {
    type: 2,
    height: int
    width: int
}

Square = {
    type: 3,
    size: int,
}

```

## JSON requests with shapes
```json
{
  "shapes": [
    {
      "type": 0,    // Triangle
      "height": 3,
      "width": 4
    },
    {
      "type": 1,    // Circle
      "radius": 12
    },
    {
      "type": 2,    // Rectangle
      "height": 4,
      "width": 5
    },
    {
      "type": 3,    // Square
      "size": 9
    }
  ]
}
```