package Week6.HotelAssignment.config;

import Week6.HotelAssignment.DTO.UserDTO;
import Week6.HotelAssignment.Exceptions.ApiException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.HttpStatus;

import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

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
        app.exception(IllegalStateException.class, (e, ctx) -> {
            ObjectNode json = om.createObjectNode();
            json.put("errorMessage", e.getMessage());
            e.printStackTrace();
            ctx.status(500).json(json);
        });
        app.exception(Exception.class, (e, ctx) -> {
            ObjectNode json = om.createObjectNode();
            json.put("errorMessage",e.getMessage());
            e.printStackTrace();
            ctx.status(500).json(json);
        });
        app.error(404, ctx -> {
            ObjectNode json = om.createObjectNode();
            json.put("errorMessage", "Not found");
            ctx.status(404).json(json);
        });
        return instance;
    }

    public ApplicationConfig checkSecurityRoles(boolean isTesting) {
        // Check roles on the user (ctx.attribute("username") and compare with permittedRoles using securityController.authorize()
        app.updateConfig(config -> {

            config.accessManager((handler, ctx, permittedRoles) -> {
                // permitted roles are defined in the last arg to routes: get("/", ctx -> ctx.result("Hello World"), Role.ANYONE);

                Set<String> allowedRoles = permittedRoles.stream().map(role -> role.toString().toUpperCase()).collect(Collectors.toSet());
                if(allowedRoles.contains("ANYONE") || ctx.method().toString().equals("OPTIONS")) {
                    // Allow requests from anyone and OPTIONS requests (preflight in CORS)
                    handler.handle(ctx);
                    return;
                }

                UserDTO user = ctx.attribute("user");
                System.out.println("USER IN CHECK_SEC_ROLES: "+user);
                if(user == null) {
                    ctx.status(HttpStatus.FORBIDDEN)
                            .json(om.createObjectNode()
                                    .put("msg", "Not authorized. No username were added from the token"));
                }
                if (authorize(user, allowedRoles)){
                    handler.handle(ctx);

                }else{
                    try {
                        throw new ApiException(HttpStatus.FORBIDDEN.getCode(), "Unauthorized with roles: "+allowedRoles);
                    } catch (ApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        });
        return instance;
    }

    public boolean authorize(UserDTO user, Set<String> allowedRoles) {
        // Called from the ApplicationConfig.setSecurityRoles

        AtomicBoolean hasAccess = new AtomicBoolean(false); // Since we update this in a lambda expression, we need to use an AtomicBoolean
        if (user != null) {
            user.getRoles().stream().forEach(role -> {
                if (allowedRoles.contains(role)) {
                    hasAccess.set(true);
                }
            });
        }
        return hasAccess.get();
    }
}
