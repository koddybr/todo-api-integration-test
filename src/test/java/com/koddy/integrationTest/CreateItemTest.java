package com.koddy.integrationTest;

import com.koddy.factoryRequest.FactoryRequest;
import com.koddy.factoryRequest.RequestInfo;
import com.koddy.request.CreateRequest;
import com.koddy.util.ApiConfiguration;
import com.koddy.util.JsonUtil;
import com.koddy.util.Random;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class CreateItemTest {

    protected Response response;

    RequestInfo requestInfo = new RequestInfo();

    @Test
    public void verifyCreateItem() throws Exception{

        CreateRequest data = new CreateRequest();
        data.setContent(Random.generateItem());

        requestInfo.setUrl(ApiConfiguration.CREATE_ITEM);
        requestInfo.setBody(JsonUtil.json(data));

        response = FactoryRequest.make("post").send(requestInfo);
        response.then().body("Content",equalTo(data.getContent())).statusCode(200);
        Integer idItem = response.then().extract().path("Id");
        Assertions.assertNotNull(idItem);
    }

    @Test
    public void verifyCreateEmptyItem() throws Exception{

        CreateRequest data = new CreateRequest();


        requestInfo.setUrl(ApiConfiguration.CREATE_ITEM);
        requestInfo.setBody(JsonUtil.json(data));

        response = FactoryRequest.make("post").send(requestInfo);
        response.then().body("ErrorMessage",equalTo("Too Short Item Name")).statusCode(200);
        Integer errorCode = response.then().extract().path("ErrorCode");
        Assertions.assertEquals(errorCode,308);
    }

    @Test
    public void verifyCreateItemTwice() throws Exception{

        CreateRequest data = new CreateRequest();
        data.setContent(Random.generateItem());


        requestInfo.setUrl(ApiConfiguration.CREATE_ITEM);
        requestInfo.setBody(JsonUtil.json(data));

        response = FactoryRequest.make("post").send(requestInfo);
        response.then().body("Content",equalTo(data.getContent())).statusCode(200);
        Integer idItem = response.then().extract().path("Id");
        Assertions.assertNotNull(idItem);

        response = FactoryRequest.make("post").send(requestInfo);
        response.then().body("Content",equalTo(data.getContent())).statusCode(200);
        Integer idItem2 = response.then().extract().path("Id");
        Assertions.assertNotNull(idItem2);

        Assertions.assertNotEquals(idItem, idItem2);
    }
}

 //"ErrorMessage": "Too Short Item Name",
   //      "ErrorCode": 308