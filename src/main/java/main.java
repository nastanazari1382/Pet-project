import controllers.UserController;
import configuration.ThymeleafConfig;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;


public class main {

    public static void main(String[] args) {


        Javalin app = Javalin.create(config -> {
            UserController.setRoutes(config);

            config.staticFiles.add("/public");

            config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));

        }).start(7070);


}
}
