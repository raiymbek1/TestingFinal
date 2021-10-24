package kz.edu.astanait;

import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class ClientCrud {
    private static String baseUri = "http://localhost:8080/RestfulMiniProject_war_exploded/rest/products";
    static WebTarget getWebTarget () {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        return client.target(baseUri);
    }

}
