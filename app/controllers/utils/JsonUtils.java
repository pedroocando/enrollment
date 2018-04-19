package controllers.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.modelmapper.ModelMapper;
import play.libs.Json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nisa on 28/04/17.
 */
public class JsonUtils {

    public static JsonNode toJson(Object object, Class typeDest){
        if(object != null)
            object = new ModelMapper().map(object, typeDest);
        return Json.toJson(object);
    }

    public static JsonNode toJson(List<?> objectSources, Class typeDest){
        List<Object> objectDests = new ArrayList<>();
        for(Object objectSource: objectSources)
            objectDests.add(new ModelMapper().map(objectSource, typeDest));
        return Json.toJson(objectDests);
    }

    public static ObjectNode toJson(Object object, String jsonName){
        JsonNode json = Json.toJson(object);

        ObjectNode result = Json.newObject();
        result.set(jsonName, json);
        return result;
    }

    public static ObjectNode toJson(Object object, Class typeDest, String jsonName){
        if(object != null)
            object = new ModelMapper().map(object, typeDest);
        JsonNode json = Json.toJson(object);

        ObjectNode result = Json.newObject();
        result.set(jsonName, json);
        return result;
    }

    public static ObjectNode toJson(List<?> objectSources, Class typeDest, String jsonName){
        List<Object> objectDests = new ArrayList<>();
        for(Object objectSource: objectSources)
            objectDests.add(new ModelMapper().map(objectSource, typeDest));
        JsonNode json = Json.toJson(objectDests);

        ObjectNode result = Json.newObject();
        result.set(jsonName, json);
        return result;
    }

    public static ObjectNode merge(List<ObjectNode> objectNodes){
        ObjectNode result = Json.newObject();
        for(ObjectNode objectNode: objectNodes)
            result.setAll(objectNode);
        return result;
    }

    public static List<Integer> toArrayInt(JsonNode jsonNode, String fieldName){
        List<Integer> numbers = new ArrayList<>();
        for(JsonNode objNode : jsonNode.get(fieldName))
            if(objNode.isNumber())
                numbers.add(objNode.asInt());
        return numbers;
    }
}
