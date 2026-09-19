package controllers;

import io.javalin.http.Context;
import services.UserService;

public class UserController {

    public static void login(Context ctx, UserService userService) {



        String username = ctx.formParam("email");
        String password = ctx.formParam("mpass");


        // hent data fra request
        // kald service
        // send response
    }

}
