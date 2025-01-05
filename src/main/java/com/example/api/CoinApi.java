package com.example.api;

import com.example.core.CoinRequest;
import com.example.core.CoinService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("/api/min-coins")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CoinApi {
    @POST
    public static Response getMinCoins(CoinRequest coinRequest) {
        double targetAmount = coinRequest.getTargetAmount();
        List<Object> rawValues = coinRequest.getValue();
        List<Double> denominations = new ArrayList<>();

        if (rawValues == null || rawValues.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "denominations cannot be empty"))
                    .build();
        }

        for (Object value : rawValues) {
            try {
                // 尝试将 Object 转换为 Double
                denominations.add(Double.parseDouble(value.toString()));
            } catch (NumberFormatException e) {
                // 如果转换失败，返回错误
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(Map.of("error", "denominations contains invalid value: " + value))
                        .build();
            }
        }

        if (denominations.stream().anyMatch(d -> d <= 0)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "denominations cannot be minus or zero"))
                    .build();
        }

        try {
            List<Double> result = CoinService.calculate(targetAmount, denominations);
            return Response.ok(Map.of("coins", result)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", e.getMessage()))
                    .build();
        }
    }


    @GET
    public Response getApiInfo() {
        return Response.ok(Map.of(
                "message", "Welcome to the Coin Exchange API! Use POST method with targetAmount and value in the body."
        )).build();
    }

}
