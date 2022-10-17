package com.koddy.factoryRequest;

import com.koddy.util.GetProperties;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestPUT implements IRequest{
    @Override
    public Response send(RequestInfo info) {
        Response response =given()
                .auth()
                .preemptive()
                .basic(GetProperties.getInstance().getUser(),
                        GetProperties.getInstance().getPwd())
                .body(info.getBody())
                .log().all()
        .when()
                .put(info.getUrl());

        response.then().log().all();
        return response;
    }
}
