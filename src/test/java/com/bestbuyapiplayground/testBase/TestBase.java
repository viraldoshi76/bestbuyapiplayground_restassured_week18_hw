package com.bestbuyapiplayground.testBase;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;

import static io.restassured.RestAssured.given;

public class TestBase extends Properties {


    @BeforeClass
    public static void inIt(){

        RestAssured.baseURI="http://localhost";
        RestAssured.port=3030;
        Properties properties=new Properties();
        if(properties.resources=="products"){
            RestAssured.basePath="/products";
        }else if(properties.resources=="stores"){
            RestAssured.basePath="/stores";
        }else if(properties.resources=="categories"){
            RestAssured.basePath="/categories";
        }else if(properties.resources=="services"){
            RestAssured.basePath="/services";
        }

    }
}
