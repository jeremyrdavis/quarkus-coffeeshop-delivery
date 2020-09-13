package com.redhat.demos.quarkus.coffeeshop.infrastructure;

import com.redhat.demos.quarkus.coffeeshop.domain.Order;
import com.redhat.demos.quarkus.coffeeshop.domain.favfood.FavFoodOrder;
import com.redhat.quarkus.cafe.domain.OrderInCommand;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletableFuture;

@Path("/api/favfood")
public class FavFoodResource {

    Logger logger = LoggerFactory.getLogger(FavFoodResource.class);

    private Jsonb jsonb = JsonbBuilder.create();

    @Inject
    Order order;

    @Inject
    @Channel("orders-out")
    Emitter<OrderInCommand> orderInCommandEmitter;

    @POST
    public CompletableFuture<Response> processFavFoodOrder(FavFoodOrder favFoodOrder) {
        logger.debug("received {}", favFoodOrder);
        OrderInCommand orderInCommand = order.processFavFoodOrder(favFoodOrder);
        logger.debug("sending {}", orderInCommand);
        return orderInCommandEmitter.send(orderInCommand)
                .handle((res, ex) -> {
                    if (ex != null) {
                        logger.error(ex.getMessage());
                        return Response.serverError().entity(ex).build();
                    }else{
                        return Response.accepted().entity(orderInCommand).build();
                    }
                }).toCompletableFuture();
    }
}
