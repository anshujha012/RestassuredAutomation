package DataDriven;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class datadriven {
    @Test(dataProvider = "empdataprovider")
    void AddNewEmployees(String ename,String esalary, String eage) {
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        RequestSpecification httpRequest = RestAssured.given();

        JSONObject requestparameter = new JSONObject();
        requestparameter.put("name", ename);
        requestparameter.put("salary", esalary);
        requestparameter.put("age", eage);

        httpRequest.body(requestparameter.toJSONString());

        // Send a request


        Response response = httpRequest.request(Method.POST, "/create");
        String responsebody = response.getBody().asString();
        System.out.println(responsebody);
        Assert.assertEquals(responsebody.contains(ename),true);
        Assert.assertEquals(responsebody.contains(esalary),true);
        Assert.assertEquals(responsebody.contains(eage),true);


        int statusCode = response.statusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode, 200);

        //String successCode = response.jsonPath().get("SuccessCode");
       // Assert.assertEquals(successCode, "OPERATION_SUCCESS");
    }
    @DataProvider(name="empdataprovider")
    Object[][] getEmployeeData() throws IOException {

          String path= System.getProperty("user.dir")+"/src/test/java/DataDriven/customerdetails.xlsx";
          int rowcount=XLUtils.getRowCount(path,"Sheet1");
          int columncount=XLUtils.getCellCount(path,"Sheet1",1);

          String empdata[][]=new String[rowcount][columncount];
          for(int i=1;i<rowcount;i++)
          {
              for(int j=0;j<columncount;j++)
              {
                  empdata[i-1][j]=XLUtils.getCellData(path,"Sheet1",i,j);
              }
          }
          return empdata;

      }

}
