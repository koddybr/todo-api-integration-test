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

public class DeleteItemTest {

    protected Response response;


    RequestInfo requestInfo = new RequestInfo();


    @Test
    public void verifyDeleteItem() throws Exception{

        CreateRequest data = new CreateRequest();
        data.setContent(Random.generateItem());

        requestInfo.setUrl(ApiConfiguration.CREATE_ITEM);
        requestInfo.setBody(JsonUtil.json(data));

        response = FactoryRequest.make("post").send(requestInfo);
        response.then().body("Content",equalTo(data.getContent())).statusCode(200);
        Integer idItem = response.then().extract().path("Id");

        requestInfo.setUrl(String.format(ApiConfiguration.DELETE_ITEM,idItem));
        //requestInfo.setBody();
        response= FactoryRequest.make("delete").send(requestInfo);
        response.then().body("Content",equalTo(data.getContent())).statusCode(200);
        Integer idItemDelete = response.then().extract().path("Id");

        Assertions.assertEquals(idItem, idItemDelete);

    }

}
