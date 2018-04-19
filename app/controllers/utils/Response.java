package controllers.utils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import play.mvc.Result;


import static play.mvc.Results.*;
public class Response{

    public static ObjectNode buildExtendResponse(String message, JsonNode result){
        ObjectNode response = Json.newObject();
        response.put("message", message);
        response.set("result", result);
        return response;
    }

    public static ObjectNode buildExtendResponse(String message, String code){
        ObjectNode response = Json.newObject();
        response.put("code", code);
        response.put("message", message);
        return response;
    }

    public static ObjectNode buildExtendResponse(String message, JsonNode result, JsonNode pager){
        ObjectNode response = Json.newObject();
        response.put("message", message);
        response.set("result", result);
        response.set("pager", pager);
        return response;
    }

    public static ObjectNode buildExtendResponse(String message){
        ObjectNode response = Json.newObject();
        response.put("message", message);
        return response;
    }

    /*
     * ok 200
     */
    public static Result all_ok(){
        return status(200, buildExtendResponse("Enrollments processed","success"));
    }


}