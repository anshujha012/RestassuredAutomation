package RestAssured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TC001_GET_Request {

    @Test
    void getweatherDetails()
    {
        RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest= RestAssured.given();
        Response response=httpRequest.request(Method.GET,"/Hyderabad");
        String responsebody=response.getBody().asString();
        System.out.println(responsebody);
        int statusCode=response.statusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode,200);


    }





}
