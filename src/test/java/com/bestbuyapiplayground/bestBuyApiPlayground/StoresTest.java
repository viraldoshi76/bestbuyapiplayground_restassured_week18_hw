package com.bestbuyapiplayground.bestBuyApiPlayground;

import com.bestbuyapiplayground.model.StoresPojo;
import com.bestbuyapiplayground.testBase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StoresTest extends TestBase{

    /*@BeforeClass

    *//*public static void inIt(){

        RestAssured.basePath="/stores";

    }
*/

    Response response;
    //Get Request
    @Test
    public void getAllStoresData(){

      response=given()
                .when()
                .get();
      response.then().statusCode(200);
      response.prettyPrint();

    }

    @Test
    public void getSingleStoresData(){

        response=given()
                .pathParam("id",12)
                .when()
                .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void getStoresDataWithParameter(){

        response=given()
                .queryParam("city","Saint Cloud")
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }

    //Post Request
    @Test
    public void createStoresData(){

        StoresPojo storesPojo=new StoresPojo();
        storesPojo.setName("Tesco23");
        storesPojo.setType("Grocery");
        storesPojo.setAddress("12341 Reeds");
        storesPojo.setAddress2("768");
        storesPojo.setCity("London");
        storesPojo.setState("MN");
        storesPojo.setZip("456372");
        storesPojo.setLat(44.9674);
        storesPojo.setLng(-94.8712);
        storesPojo.setHours("Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-5");

        response=given()
                .header("Content-Type","application/json")
                .body(storesPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }

    //Put Request
    @Test

    public void updateStoresRecord(){


        StoresPojo storesPojo=new StoresPojo();
        storesPojo.setName("Tesco");
        storesPojo.setType("Grocery");
        storesPojo.setAddress("12341 Reeds");
        storesPojo.setAddress2("");
        storesPojo.setCity("LEICESTER");
        storesPojo.setState("MN");
        storesPojo.setZip("456372");
        storesPojo.setLat(44.9674);
        storesPojo.setLng(-94.8712);
        storesPojo.setHours("Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-5");

        response=given()
                .pathParam("id","8925")
                .header("Content-Type","application/json")
                .body(storesPojo)
                .when()
                .put("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    //Patch Request
    @Test

    public void updateStoreRecordWithPatch(){

       StoresPojo storesPojo=new StoresPojo();
       storesPojo.setName("TESCO");

       response=given()
               .pathParam("id","8925")
               .header("Content-Type","application/json")
               .body(storesPojo)
               .when()
               .patch("/{id}");
       response.then().statusCode(200);
       response.prettyPrint();

   }

   //Delete Request
    @Test

    public void deleteStoresData(){

        response=given()
                .pathParam("id",8925)
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
}
