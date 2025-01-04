package com.example.api;

import com.example.core.CoinRequest;
import com.example.core.CoinService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

@Path("/api/min-coins")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CoinApi {
    @POST
    public static Response getMinCoins(CoinRequest coinRequest){
        double targetAmount = coinRequest.getTargetAmount();
        List<Double> denominations = coinRequest.getValue();

        if (denominations == null || denominations.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "denominations cannot be empty"))
                    .build();
        }
        if (denominations.stream().anyMatch(d -> d <= 0)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "denominations cannot be minus or zero"))
                    .build();
        }

        try{
            List<Double> result = CoinService.calculate(targetAmount, denominations);
            return Response.ok(Map.of("coins", result)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", e.getMessage()))
                    .build();
        }
    }

}
