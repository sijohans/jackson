package se.omegapoint.jackson.shapes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ShapesFuzzer {

    private final static ObjectMapper mapper = new ObjectMapper();

    @SuppressWarnings("unused")
    public static void fuzzerTestOneInput(com.code_intelligence.jazzer.api.FuzzedDataProvider data) {
        parse(data.consumeRemainingAsString());
    }

    public static void parse(String input) {
        try {
            Shapes shapesObj = mapper.readValue(input, Shapes.class);
            if (!(shapesObj != null && shapesObj.getShapes() != null)) {
                throw new NullPointerException("Something was null"); /* Trigger crash */
            }

            for (Shape shape : shapesObj.getShapes()) {
                boolean validInstance = false;
                switch (shape.getType()) {
                    case TRIANGLE:
                        validInstance = shape instanceof Triangle;
                        break;
                    case CIRCLE:
                        validInstance = shape instanceof Circle;
                        break;
                    case SQUARE:
                        validInstance = shape instanceof Square;
                        break;
                    case RECTANGLE:
                        validInstance = shape instanceof Rectangle;
                        break;
                }
                if (!validInstance) {
                    throw new IllegalStateException("Bad instance...");
                }
            }

        } catch(JsonProcessingException e) {
            /* Expected */
        }
    }

    public static void main(String[] args) {
        String jsonRep = "{\"shapes\":[{\"type\":0,\"height\":3,\"width\":4},{\"type\":1,\"radius\":12},{\"type\":2,\"height\":4,\"width\":5},{\"type\":3,\"size\":9}]}";
        parse(jsonRep);
    }


}
