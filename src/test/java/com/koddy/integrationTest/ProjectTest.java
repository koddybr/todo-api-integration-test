package com.koddy.integrationTest;

import com.koddy.factoryRequest.FactoryRequest;
import com.koddy.factoryRequest.RequestInfo;
import com.koddy.util.ApiConfiguration;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class ProjectTest {
    Response response;
    JSONObject body= new JSONObject();
    RequestInfo requestInfo = new RequestInfo();
    @Test
    public void verifyCRUDProject(){

        body.put("Content","Cato2022");
        requestInfo.setUrl(ApiConfiguration.CREATE_PROJECT);
        requestInfo.setBody(body.toString());

        response= FactoryRequest.make("post").send(requestInfo);
        response.then().body("Content",equalTo(body.get("Content"))).statusCode(200);
        int idProj=response.then().extract().path("Id");

        body.put("Content","CatoUpdated2022");
        requestInfo.setUrl(String.format(ApiConfiguration.UPDATE_PROJECT,idProj));
        requestInfo.setBody(body.toString());
        response= FactoryRequest.make("put").send(requestInfo);
        response.then().body("Content",equalTo(body.get("Content"))).statusCode(200);

        requestInfo.setUrl(String.format(ApiConfiguration.READ_PROJECT,idProj));
        requestInfo.setBody(body.toString());
        response= FactoryRequest.make("get").send(requestInfo);
        response.then().body("Content",equalTo(body.get("Content"))).statusCode(200);

        requestInfo.setUrl(String.format(ApiConfiguration.DELETE_PROJECT,idProj));
        requestInfo.setBody(body.toString());
        response= FactoryRequest.make("delete").send(requestInfo);
        response.then().body("Content",equalTo(body.get("Content"))).statusCode(200);

    }

}
