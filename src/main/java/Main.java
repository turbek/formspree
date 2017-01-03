import db.ConnectionHandler;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {

//        ConnectionHandler connectionHandler = new ConnectionHandler();

        post("/:email", (request, response) -> {
            Utils.sendMessage(request);
            return "Sikerke";
        });
    }
}