import static spark.Spark.post;

public class Main {
    public static void main(String[] args) {
        post("/:email", (request, response) -> {
            System.out.println(request.params(":email"));
            Utils.sendMessage(request, request.params(":email"));
            return "Sikerke";
        });
    }
}