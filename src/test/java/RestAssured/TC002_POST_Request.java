package RestAssured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002_POST_Request {

    @Test
    void Registration()
    {
        RestAssured.baseURI="http://restapi.demoqa.com/customer";
        RequestSpecification httpRequest= RestAssured.given();

        JSONObject requestparameter= new JSONObject();
        requestparameter.put("FirstName", "Anshuuuuuu");
        requestparameter.put("LastName", "Sharma");
        requestparameter.put("UserName", "AnshuuJha");
        requestparameter.put("Password", "Anshu123");
        requestparameter.put("Email", "Anshu.jha0012@gmail.com");
        httpRequest.header("Content-Type","application/json");
        httpRequest.body(requestparameter.toJSONString());

      // Send a request


        Response response=httpRequest.request(Method.POST,"/register");
        String responsebody=response.getBody().asString();
        System.out.println(responsebody);
        int statusCode=response.statusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode,201);

        String  successCode=response.jsonPath().get("SuccessCode");
        Assert.assertEquals(successCode,"OPERATION_SUCCESS");


    }
}
