package org.acme;

import io.quarkus.narayana.jta.QuarkusTransaction;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import javax.sql.DataSource;

@Path("/hello")
public class GreetingResource {
    @Inject
    DataSource ds;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws Exception {
        QuarkusTransaction.requiringNew()
                .run(() -> {
                    try (var c = ds.getConnection()) {

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        return "Hello from RESTEasy Reactive";
    }
}
