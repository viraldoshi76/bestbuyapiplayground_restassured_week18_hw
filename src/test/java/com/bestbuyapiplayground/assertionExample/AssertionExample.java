package com.bestbuyapiplayground.assertionExample;

import com.bestbuyapiplayground.testBase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AssertionExample {

    static ValidatableResponse response;
    @BeforeClass
    public static void inIt(){

        RestAssured.baseURI="http://localhost";
        RestAssured.port=3030;
        response=given()
                .when()
                .get("/stores")
                .then();
    }

    //1. Verify the if the total is equal to 1560
    @Test
    public void verifyTotal(){

       response.body("total",equalTo(1563));

    }

    //2. Verify the if the stores of limit is equal to 10
    @Test
    public void verifyLimit(){

        response.body("limit",equalTo(10));
    }

    //3. Check the single ‘Name’ in the Array list (Inver Grove Heights)
    @Test
    public void verifySingleName(){

        response.body("data.name",hasItem("Inver Grove Heights"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Square83215, Southridge, Green Bay)
    @Test
    public void verifyMultipleName(){

        response.body("data.name",hasItems("Inver Grove Heights, Roseville, Burnsville, Maplewood, Northtown, string, Fargo, Rochester, Oakdale, West Des Moines"));
    }

   // 5. Verify the storied inside storeservices of the third store of second services
    @Test
    public void verifyStoreId(){

        response.body("data[1].services[2].storeservices",hasKey("storeId"));
    }

    //6. Check hash map values ‘createdAt’ inside store services map where store name = Southridge
    @Test
    public void verifyCreatedAt(){

        response.body("data.findAll{it.name=='Roseville'}",hasItems(hasEntry("createdAt","2016-11-17T17:57:05.853Z")));
    }

    //7. Verify the state = MN of third store
    @Test
    public void verifyState(){
        response.body("data.findAll{it.name=='Burnsville'}",hasItems(hasEntry("state","MN")));
    }

    //8. Verify the name = Green Bay of 9th store
    @Test
    public void verifyName(){

        response.body("data.findAll{it.id==15}",hasItem(hasEntry("name","Oakdale")));
    }

    //9. Verify the storeId = 12 for the 6th store
    @Test
    public void verifyStoreIdOfSixthStore(){

        response.body("data.findAll{it.id==7}",hasItem(hasEntry("services.storeservices.storeId",12)));
    }


    //10. Verify the serviceId = 14 for the 7th store


}
