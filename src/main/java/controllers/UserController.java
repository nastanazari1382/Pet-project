package controllers;

import io.javalin.http.Context;
import services.UserService;

public class UserController {

    public static void login(Context ctx, UserService userService) {




        String email = ctx.formParam("loginEmail");
        String password = ctx.formParam("loginPass");


        if(userService.login(email,password) != null){
            ctx.redirect("/welcome");
        } else {
            ctx.redirect("/login");
        }



    }


}
