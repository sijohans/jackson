package se.omegapoint.jackson.shapes;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShapesDeserializer extends StdDeserializer<Shapes> {
    public ShapesDeserializer() {
        this(null);
    }
    public ShapesDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Shapes deserialize(JsonParser jp, DeserializationContext dc) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode head = jp.getCodec().readTree(jp);
        ArrayNode shapes = mapper.treeToValue(head.get("shapes"), ArrayNode.class);

        if (shapes == null) {
            throw MismatchedInputException.from(jp, ArrayNode.class, "Missing required creator property 'shapes'");
        }

        List<Shape> ret = new ArrayList<>(shapes.size());
        for (int i = 0; i < shapes.size(); i++) {
            JsonNode shape = shapes.get(i);
            JsonNode shapeNode = shape.get("type");
            if (shapeNode == null) {
                throw MismatchedInputException.from(jp, ArrayNode.class, "Missing required creator property 'type'");
            }
            ShapeType type = mapper.treeToValue(shapeNode, ShapeType.class);
            switch (type) {
                case TRIANGLE:
                    ret.add(mapper.treeToValue(shape, Triangle.class));
                    break;
                case CIRCLE:
                    ret.add(mapper.treeToValue(shape, Circle.class));
                    break;
                case RECTANGLE:
                    ret.add(mapper.treeToValue(shape, Rectangle.class));
                    break;
                case SQUARE:
                    ret.add(mapper.treeToValue(shape, Square.class));
                    break;
            }
        }

        return new Shapes(ret);
    }
}
