package Week6.HotelAssignment.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;

public class ApplicationConfig {
    ObjectMapper om = new ObjectMapper();
    private Javalin app;
    private static ApplicationConfig instance;
    private ApplicationConfig(){}

    public static ApplicationConfig getInstance(){
        if (instance == null){
            instance = new ApplicationConfig();
        }
        return instance;
    }
    public ApplicationConfig initiateServer(){
        app = app.create(config -> {
            config.http.defaultContentType = "application/json";
            config.routing.contextPath = "/api";
        });
        return instance;
    }

    public ApplicationConfig startServer(int port){
        app.start(port);
        return instance;
    }

    public ApplicationConfig setRoute(EndpointGroup route){
        app.routes(route);
        return instance;
    }
    public ApplicationConfig setExceptionHandling(){
        app.exception(IllegalStateException.class,(e,ctx) ->{
            ObjectNode node = om.createObjectNode().put("IllegalStateException: When posting or updating a hotel or room with incorrect json representation.",e.getMessage());
            ctx.status(102030401).json(node);
        });
        app.exception(NumberFormatException.class,(e,ctx) ->{
            ObjectNode node = om.createObjectNode().put("Bad request: Not a number!",e.getMessage());
            ctx.status(400).json(node);
        });
        app.exception(NullPointerException.class,(e,ctx) -> {
            ObjectNode node = om.createObjectNode().put("Bad request: Not found!",e.getMessage());
            ctx.status(404).json(node);
        });
        app.exception(Exception.class, (e,ctx) ->{
            ObjectNode node = om.createObjectNode().put("errorMessage",e.getMessage());
            ctx.status(500).json(node);
        });
        return instance;
    }
}
