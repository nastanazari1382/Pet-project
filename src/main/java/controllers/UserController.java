package controllers;

import entities.User;
import exceptions.IllegalUserDataException;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;
import services.UserService;

import java.util.Objects;

public class UserController {
    private static UserService userService = new UserService();

    public static void setRoutes(JavalinConfig config){

        config.routes.get("/", ctx -> ctx.redirect("/register"));
        config.routes.get("/register", ctx -> ctx.render("templates/register.html"));
        config.routes.post("/register", ctx -> createUser(ctx));

        config.routes.get("/login", ctx -> ctx.render("templates/login.html"));
        config.routes.post("/login", ctx -> UserController.login(ctx));


        config.routes.get("/welcome", ctx -> {
            User user = ctx.sessionAttribute("user");
            if (user != null) {
                ctx.render("templates/welcome.html");
            } else {
                ctx.redirect("/login");
            }
        });

    }

    public static void setUserService(UserService service) {
        userService = service;
    }
    public static void createUser(Context ctx) throws IllegalUserDataException {

        String email = ctx.formParam("email");
        String password = ctx.formParam("mpass");
        String cmpass = ctx.formParam("cmpass");

        User user = userService.getUser(email);

        if (!userService.validatePassword(password)) {
            ctx.attribute("error", "Password must be between 8 and 16 characters");
            ctx.render("templates/register");
        } else {
            if(user==null){
                userService.createUser(email, password);
                ctx.redirect("/login");
            } else {
                ctx.attribute("error", "User already exists");
                ctx.render("templates/register");
            }

        }

    }


    public static void login(Context ctx) {
        String email = ctx.formParam("loginEmail");
        String password = ctx.formParam("loginPass");
        User user = userService.login(email, password);

        if (user != null) {
            ctx.sessionAttribute("user", user);
            ctx.redirect("/welcome");
        } else {
            ctx.attribute("error", "Invalid email or password");
            ctx.render("templates/login");
        }
    }
}
