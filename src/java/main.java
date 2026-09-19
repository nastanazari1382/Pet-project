import entities.User;
import factories.UserFactory;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;
import services.UserService;

public class main {

    public static void main(String[] args) {

        UserFactory userFactory= new UserFactory();
        UserService userService = new UserService(userFactory);



        userService.add10();
        System.out.println(userFactory.getUsers());
        System.out.println(userService.getUser("Bowen"));


        System.out.println(userService.login("peter","43214"));
        System.out.println(userService.login("peter","33333"));


        System.out.println(userService.createUser("peter1","44444"));



        var app = Javalin.create(config -> {

            config.staticFiles.add("/public");

            config.fileRenderer(new JavalinThymeleaf());



            config.routes.get("/", ctx -> ctx.result("Hej verden"));

            config.routes.get("/hello", ctx -> ctx.result("hello"));


            config.routes.get("/register", ctx -> {
                ctx.render("templates/register.html");

            });

            config.routes.post("/register", ctx -> {


                String email = ctx.formParam("email");
                String password = ctx.formParam("mpass");


                userService.createUser(email,password);

                ctx.sessionAttribute("email", email);


                ctx.redirect("/login");

            });

            config.routes.get("/login", ctx -> {

                ctx.render("templates/login.html");
            });


            config.routes.post("/login", ctx -> {


                String email = ctx.formParam("logemail");


                if(userService.login(email,"0000") != null){
                    ctx.redirect("/welcome");
                } else {
                    ctx.redirect("/login");
                }



            });


            config.routes.get("/welcome", ctx -> {
                ctx.render("templates/welcome.html");
            });


            config.routes.post("/welcome", ctx -> {
                ctx.redirect("/welcome");
            });




        }).start(7070);



}
}
