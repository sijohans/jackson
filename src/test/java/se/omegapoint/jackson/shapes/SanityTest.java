package se.omegapoint.jackson.shapes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static se.omegapoint.jackson.shapes.ShapeType.*;

public class SanityTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testShapesListSanity() throws JsonProcessingException {
        String jsonRep = "{\"shapes\":[{\"type\":0,\"height\":3,\"width\":4},{\"type\":1,\"radius\":12},{\"type\":2,\"height\":4,\"width\":5},{\"type\":3,\"size\":9}]}";
        Shapes shapesObj = Shapes.fromString(jsonRep);
        Assert.assertNotNull(shapesObj);
        List<Shape> shapes = shapesObj.getShapes();
        Assert.assertNotNull(shapes);
        Assert.assertEquals(4, shapes.size());

        Assert.assertEquals(TRIANGLE, shapes.get(0).getType());
        Assert.assertTrue(shapes.get(0) instanceof Triangle);

        Assert.assertEquals(CIRCLE, shapes.get(1).getType());
        Assert.assertTrue(shapes.get(1) instanceof Circle);

        Assert.assertEquals(RECTANGLE, shapes.get(2).getType());
        Assert.assertTrue(shapes.get(2) instanceof Rectangle);

        Assert.assertEquals(SQUARE, shapes.get(3).getType());
        Assert.assertTrue(shapes.get(3) instanceof Square);
    }

    @Test
    public void testTriangleSanity() throws JsonProcessingException {
        String jsonRep = "{\"type\": 0,\"height\":3,\"width\":4}";
        Triangle object = mapper.readValue(jsonRep, Triangle.class);
        Assert.assertNotNull(object);
        Assert.assertEquals(TRIANGLE, object.getType());
        Assert.assertEquals(3, object.getHeight());
        Assert.assertEquals(4, object.getWidth());
    }

    @Test
    public void testCircleSanity() throws JsonProcessingException {
        String jsonRep = "{\"type\": 1,\"radius\":5}";
        Circle object = mapper.readValue(jsonRep, Circle.class);
        Assert.assertEquals(CIRCLE, object.getType());
        Assert.assertEquals(5, object.getRadius());
    }

    @Test
    public void testRectangleSanity() throws JsonProcessingException {
        String jsonRep = "{\"type\": 2,\"height\":4,\"width\":3}";
        Rectangle object = mapper.readValue(jsonRep, Rectangle.class);
        Assert.assertNotNull(object);
        Assert.assertEquals(RECTANGLE, object.getType());
        Assert.assertEquals(4, object.getHeight());
        Assert.assertEquals(3, object.getWidth());
    }

    @Test
    public void testSquareSanity() throws JsonProcessingException {
        String jsonRep = "{\"type\": 3,\"size\":7}";
        Square object = mapper.readValue(jsonRep, Square.class);
        Assert.assertNotNull(object);
        Assert.assertEquals(SQUARE, object.getType());
        Assert.assertEquals(7, object.getSize());
    }

}
