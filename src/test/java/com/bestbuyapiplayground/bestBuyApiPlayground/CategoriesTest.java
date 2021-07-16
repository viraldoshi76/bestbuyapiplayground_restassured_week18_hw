package com.bestbuyapiplayground.bestBuyApiPlayground;

import com.bestbuyapiplayground.model.CategoriesPojo;
import com.bestbuyapiplayground.testBase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class CategoriesTest extends TestBase {

    Response response;
    //Get Request
    @Test
    public void getAllData(){

       response=given()
                .when()
               .get();
       response.then().statusCode(200);
       response.prettyPrint();
    }

    @Test
    public void getSingleCategoriesData(){

        response=given()
                .pathParam("id","abcat0020001")
                .when()
                .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void getCategoriesDataWithParameter(){

        HashMap<String,Object> qParam=new HashMap<>();
        qParam.put("name","TV & Home Theater");
        qParam.put("$limit",1);
        response=given()
                .queryParams(qParam)
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }

    //Post Request
    @Test
    public void createCategoriesData(){

        CategoriesPojo categoriesPojo=new CategoriesPojo();
        categoriesPojo.setName("Present Ideas");
        categoriesPojo.setId("xyz00100002");
        response=given()
                .header("Content-Type","application/json")
                .body(categoriesPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }

    //Put Request
    @Test
    public void updateCategoriesDataWithPut(){

        CategoriesPojo categoriesPojo=new CategoriesPojo();
        categoriesPojo.setName("Gift Ideas");
        categoriesPojo.setId("xyz00100002");
        response=given()
                .pathParam("id","xyz00100002")
                .header("Content-Type","application/json")
                .body(categoriesPojo)
                .when()
                .put("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    //Patch Request
    @Test
    public void updateCategoriesDataWithPatch(){

        CategoriesPojo categoriesPojo=new CategoriesPojo();
        categoriesPojo.setName("Presents Ideas");
        response=given()
                .pathParam("id","xyz00100002")
                .header("Content-Type","application/json")
                .body(categoriesPojo)
                .when()
                .patch("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    //Delete Request
    @Test
    public void deleteCategoriesData(){

        response=given()
                .pathParam("id","xyz00100002")
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
}
