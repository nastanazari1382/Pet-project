import controllers.UserController;
import configuration.ThymeleafConfig;
import entities.User;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;
import services.UserService;



public class main {

    public static void main(String[] args) {


        UserService userService = new UserService();
        UserController.setUserService(userService);


        Javalin app = Javalin.create(config -> {
            UserController.setRoutes(config);

            config.staticFiles.add("/public");

            config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));

        }).start(7070);



}
}
