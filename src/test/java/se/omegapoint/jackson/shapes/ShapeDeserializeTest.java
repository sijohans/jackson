package se.omegapoint.jackson.shapes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import static se.omegapoint.jackson.shapes.ShapeType.TRIANGLE;

public class ShapeDeserializeTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testDeserializeAsShape() throws JsonProcessingException {
        String jsonRep = "{\"type\": 0,\"height\":4,\"width\":3}";
        Shape shape = mapper.readValue(jsonRep, Shape.class);
        Assert.assertNotNull(shape);
        Assert.assertEquals(TRIANGLE, shape.getType());
        Assert.assertTrue(shape instanceof Triangle);
        Triangle triangle = (Triangle) shape;
        Assert.assertEquals(4, triangle.getHeight());
        Assert.assertEquals(3, triangle.getWidth());
    }

}
