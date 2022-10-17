package com.koddy.integrationTest;

import com.koddy.factoryRequest.FactoryRequest;
import com.koddy.factoryRequest.RequestInfo;
import com.koddy.request.CreateRequest;
import com.koddy.request.UpdateRequest;
import com.koddy.util.ApiConfiguration;
import com.koddy.util.JsonUtil;
import com.koddy.util.Random;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class UpdateItemTest {

    protected Response response;


    RequestInfo requestInfo = new RequestInfo();

    @Test
    public void verifyUpdateItem() throws Exception{

        CreateRequest data = new CreateRequest();
        data.setContent(Random.generateItem());

        requestInfo.setUrl(ApiConfiguration.CREATE_ITEM);
        requestInfo.setBody(JsonUtil.json(data));

        response = FactoryRequest.make("post").send(requestInfo);
        response.then().body("Content",equalTo(data.getContent())).statusCode(200);
        Integer idItem = response.then().extract().path("Id");

        UpdateRequest check = new UpdateRequest();
        check.setChecked(true);
        requestInfo.setUrl(String.format(ApiConfiguration.UPDATE_ITEM,idItem));
        requestInfo.setBody(JsonUtil.json(check));
        response= FactoryRequest.make("put").send(requestInfo);
        response.then().body("Checked",equalTo(check.getChecked())).statusCode(200);
        Integer idItemChecked = response.then().extract().path("Id");

        Assertions.assertEquals(idItem, idItemChecked);


    }

}
