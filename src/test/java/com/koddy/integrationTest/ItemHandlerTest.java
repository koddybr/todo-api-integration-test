package com.koddy.integrationTest;

import com.koddy.factoryRequest.FactoryRequest;
import com.koddy.factoryRequest.RequestInfo;
import com.koddy.util.ApiConfiguration;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class ItemHandlerTest {

    protected Response response;

    JSONObject body= new JSONObject();

    RequestInfo requestInfo = new RequestInfo();

    @Test
    public void verifyCreateItem(){
        body.put("Content","Item10");
        requestInfo.setUrl(ApiConfiguration.CREATE_ITEM);
        requestInfo.setBody(body.toString());

        response = FactoryRequest.make("post").send(requestInfo);
        response.then().body("Content",equalTo(body.get("Content"))).statusCode(200);
        Integer idItem = response.then().extract().path("Id");
    }

    @Test
    public void verifyGetItem(){
        body.put("Content","Item30");
        requestInfo.setUrl(ApiConfiguration.CREATE_ITEM);
        requestInfo.setBody(body.toString());

        response = FactoryRequest.make("post").send(requestInfo);
        response.then().body("Content",equalTo(body.get("Content"))).statusCode(200);
        Integer idItem = response.then().extract().path("Id");

        requestInfo.setUrl(String.format(ApiConfiguration.READ_ITEM,idItem));
        requestInfo.setBody(body.toString());
        response= FactoryRequest.make("get").send(requestInfo);
        response.then().body("Content",equalTo(body.get("Content"))).statusCode(200);
    }

    @Test
    public void verifyUpdateItem(){
        body.put("Content","Item40");
        requestInfo.setUrl(ApiConfiguration.CREATE_ITEM);
        requestInfo.setBody(body.toString());

        response = FactoryRequest.make("post").send(requestInfo);
        response.then().body("Content",equalTo(body.get("Content"))).statusCode(200);
        Integer idItem = response.then().extract().path("Id");

        body.put("Checked",true);
        requestInfo.setUrl(String.format(ApiConfiguration.UPDATE_ITEM,idItem));
        requestInfo.setBody(body.toString());
        response= FactoryRequest.make("put").send(requestInfo);
        response.then().body("Content",equalTo(body.get("Content"))).statusCode(200);
    }

    @Test
    public void verifyDeleteItem(){
        body.put("Content","Item50");
        requestInfo.setUrl(ApiConfiguration.CREATE_ITEM);
        requestInfo.setBody(body.toString());

        response = FactoryRequest.make("post").send(requestInfo);
        response.then().body("Content",equalTo(body.get("Content"))).statusCode(200);
        Integer idItem = response.then().extract().path("Id");

        requestInfo.setUrl(String.format(ApiConfiguration.DELETE_ITEM,idItem));
        requestInfo.setBody(body.toString());
        response= FactoryRequest.make("delete").send(requestInfo);
        response.then().body("Content",equalTo(body.get("Content"))).statusCode(200);
    }
}
