package controllers.utils;


import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.ActorMaterializerSettings;
import com.fasterxml.jackson.databind.JsonNode;
import play.Logger;
import play.libs.ws.WSBodyReadables;
import play.libs.ws.WSBodyWritables;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.libs.ws.ahc.AhcWSClient;
import play.shaded.ahc.org.asynchttpclient.AsyncHttpClient;
import play.shaded.ahc.org.asynchttpclient.AsyncHttpClientConfig;
import play.shaded.ahc.org.asynchttpclient.DefaultAsyncHttpClient;
import play.shaded.ahc.org.asynchttpclient.DefaultAsyncHttpClientConfig;

import java.io.IOException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;


/**
 * Created by Ferck on 24/9/2017.
 */
public class ApiCalls implements WSBodyReadables, WSBodyWritables {


    final Logger.ALogger logger = Logger.of(this.getClass().getName());

    ActorSystem system;
    ActorMaterializerSettings settings;
    ActorMaterializer materializer ;


    private static ApiCalls _instance;
    public static  ApiCalls instance()
    {
        if(_instance ==  null)
            _instance =  new ApiCalls();
        return _instance;
    }

    public WSClient getWS()
    {
        AsyncHttpClientConfig asyncHttpClientConfig = new DefaultAsyncHttpClientConfig.Builder()
                .setMaxRequestRetry(0)
                .setShutdownQuietPeriod(0)
                .setShutdownTimeout(0).build();
        AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient(asyncHttpClientConfig);
        return new AhcWSClient(asyncHttpClient, materializer);
    }

    public ApiCalls()
    {
        String name = "wsclient";
        system = ActorSystem.create(name);
        settings = ActorMaterializerSettings.create(system);
        materializer = ActorMaterializer.create(settings, system, name);
        logger.info("El sistema Creo nuevo WSCHandler");
    }


    public JsonNode JsonResponsePOST(String route, JsonNode requestbody,String key) throws IOException, ExecutionException, InterruptedException {

        JsonNode responseBody;
        WSClient ws = getWS();
        CompletionStage<JsonNode> jsonPromise = ws.url(route).addHeader("key",key).post(requestbody)
                .thenApply(WSResponse::asJson);
        responseBody = jsonPromise.toCompletableFuture().get();
        ws.close();
        return responseBody;
    }



}
