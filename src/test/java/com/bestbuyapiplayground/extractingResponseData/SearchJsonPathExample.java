package com.bestbuyapiplayground.extractingResponseData;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SearchJsonPathExample {

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

    // 1) Extract limit
    @Test
    public void extractLimit() {

        int limit=response.extract().path("limit");
        System.out.println("The total number of limit is: "+limit );

    }

    // 2) Extract total
    @Test
    public void extractTotal() {

        int total=response.extract().path("total");
        System.out.println("The search query is: "+total );

    }

    // 3) Extract the name of 5th store
    @Test
    public void extractFifthStoreName() {

        String name=response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Store name is: "+name);
        System.out.println("------------------End of Test---------------------------");
    }

    //4)  Extract the names of all the store
    @Test
    public void extractAllStoresName() {
        List<String> nameList = new ArrayList<>();
        nameList=response.extract().path("data.name");
        System.out.println("All Stores name : "+nameList);

    }

    //5)  Extract the storeId of all the store
    @Test
    public void extractAllStoreId(){

        List<Integer> idList=new ArrayList<>();
        idList=response.extract().path("data.id");
        System.out.println("All Store id : "+idList);
    }

    //6) Print the size of the data list
    @Test
    public void sizeOfDataList(){

        List<Integer> idList=new ArrayList<>();
        idList=response.extract().path("data.id");
        System.out.println(idList);
        int size= idList.size();
        System.out.println("Size of the data list : "+size);
    }

    //7) Get all the value of the store where store name = Maplewood
    @Test
    public void getStoreDataWhereNameIsMaplewood(){

        List<HashMap<String,?>> values= response.extract().path("data.findAll{it.name=='Maplewood'}");
        System.out.println("Value of the store where name is Maplewood : "+values);
    }

    //8) Get the address of the store where store name = Rochester
    @Test
    public void getAddressOfRochesterStore(){

        List<Integer> address= response.extract().path("data.findAll{it.name=='Rochester'}.address");
        System.out.println("Address of Rochester : "+address);
    }

    //9)  Get all the services of 8th store
    @Test
    public void getAllServicesOfEighthStore(){
        List<HashMap<String,Object>> services =response.extract().path("data[7].services");

        System.out.println("Services of 8th stores are : ");
        for (HashMap<String,Object> service:services){
            System.out.println(service);
        }
    }

    //10) Get store services of the store where service name = Sony Experience
    @Test
    public void getStoreServicesOfSonyExperienceServices(){

        //HashMap<String,Object> services=new HashMap<>();
        List<Object> services=new ArrayList<>();
        services =response.extract().path("data.findAll{it.services.name=='Sony Experience'}.storeservices");
        System.out.println("Services stores are : "+services);

    }

    //11) Get all the storeId of all the store
    @Test
    public void GetAllStoreId(){

        List<Integer> idList=new ArrayList<>();
        idList=response.extract().path("data.services.storeservices.storeId");
        System.out.println("All Store id : "+idList);

    }

    //12) Get id of all the store
    @Test
    public void getIdOfAllStore(){

        List<Integer> idList=new ArrayList<>();
        idList=response.extract().path("data.id");
        System.out.println("All Store id : "+idList);
    }

    //13) Find the store names Where state = WI
    @Test
    public void getStoresName(){

        List<Integer> stores= response.extract().path("data.findAll{it.state=='ND'}.name");
        System.out.println("Store names : "+stores);
    }

    //14)  Find the Total number of services for the store where store name = Burnsville
    @Test
    public void getTotalNoOfServices(){
        List<HashMap<String,Object>> services =response.extract().path("data.findAll{it.name=='Burnsville'}.services");
        int size=services.size();
        System.out.println(services);
        System.out.println("Total no of Services for Burnville are : "+size);

    }

    //15) Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void getCreateAtForWindowsStore(){

        List<String> created =response.extract().path("data.findAll{it.services.name=='Windows Store'}.services.created");
        System.out.println("Created for Windows Store : "+created);
    }

    //16) Find the name of all services Where store name = “Maplewood”
    @Test
    public void getAllServicesOfMaplewood(){
        List<String> servicesName =response.extract().path("data.findAll{it.name=='Maplewood'}.services.name");
        System.out.println("Services name are : "+servicesName);

    }

    //17) Find the zip of all the store
    @Test
    public void getZipOfAllStores(){

        List<String> zipList=response.extract().path("data.zip");
        System.out.println("Zip of all stores are : "+zipList);
    }

    //18)  Find the zip of store name = Inver Grove Heights
    @Test
    public void getZipOfInverGroveHeightsStore(){

        List<String> zip=response.extract().path("data.findAll{it.name=='Inver Grove Heights'}.zip");
        System.out.println("Zip of Inver Grove Heights : "+zip);
    }

    //19) Find the storeservices details of the service name = Samsung Experience
    @Test
    public void getServicesDetails(){

          HashMap<String,Object> created =response.extract().path("data.findAll{it.services.name=='Samsung Experience'}.storeservices");
               System.out.println("Created for Windows Store : "+created);

    }

    //20)  Find the lat of all the stores
    @Test
    public void getLatOfAllStores(){

        List<Double> latList=response.extract().path("data.lat");
        System.out.println("Lat of all the stores : "+latList);
    }
}
