import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class Main {

    /**
     * The main method for the microservice
     * starts the server on port: 4567
     *
     * It redirects to an html page after sending the email
     *
     * @param args
     */
    public static void main(String[] args) {
        // TODO: change form.html email!
        staticFileLocation("/");
        post("/:email", (request, response) -> {
            try{
                Utils.sendMessage(request, request.params(":email"));
                response.redirect("html/success.html");
            }catch(Exception e){
                response.redirect("html/failure.html");
            }
            return "";
        });

    }
}