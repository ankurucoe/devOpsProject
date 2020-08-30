import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.JsonConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.*;
import org.testng.annotations.Test;
import io.restassured.*;

import java.util.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItems;
import static io.restassured.RestAssured.*;

public class workApiTest {

    static String token = "wRCLdXlTz9_eq2cit79lYW9x8WSzXSYEAc5h";
    private static RequestSpecification requestSpec;
    JsonPath jp;
    // Sample expected data
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21};
    String jsonString = "{\n" +
            "    \"name\": \"ankur\",\n" +
            "    \"job\": \"Engineer \"\n" +
            "}";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://gorest.co.in/public-api";
        requestSpec = new RequestSpecBuilder().setBaseUri("https://gorest.co.in/public-api").build();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.config().jsonConfig(JsonConfig.jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL));
    }

    @DataProvider
    public Object[][] dp() {
        Object[][] testData = new Object[][]{
                new Object[]{"Ankur", "Markanda", "male", "ankur@gmail.com", "active"},
                new Object[]{"Nacny", "Tyagi", "female", "nancy@gmail.com", "active"}};
        return testData;
    }

    @Test(description = "getting phone number from response")
    public void getRequest() throws java.net.ConnectException {
        Response r = given().headers("Accept", "application/json", "Content-Type", "application/json",
                "Authorization", "Bearer " + token).param("last_name", "Markanda") //Uncomment to run with Query Parameters
                .get("/users");
        r.then().statusCode(200).assertThat().contentType("application/json");
        //assertThat().body(matchesJsonSchemaInClasspath("")); method to validate response matches with json schema
        jp = new JsonPath(r.asString()).setRootPath("result");
        String result = jp.getString("first_name");
        System.out.println(r.asString());
    }

    @Test(dataProvider = "dp", description = "post method using data driven approach", enabled = true)
    public void postRequest(final String firstName, final String lastName, final String gender, final String email, final String status) {
        Response r = given().headers("Accept", "application/json", "Content-Type", "application/json",
                "Authorization", "Bearer " + token)
                .body(new HashMap<String, Object>() {
                    {
                        put("first_name", firstName);
                        put("last_name", lastName);
                        put("gender", gender);
                        put("email", email);
                        put("status", status);
                    }
                })
                .when().post("/users");
        jp = new JsonPath(r.asString()).setRootPath("result");
        System.out.println(jp.getString("message"));

    }

    @Test
    public void jsonPathValidation() {
        Response res =
                given()
                        .spec(requestSpec)
                        .headers("Accept", "application/json", "Content-Type", "application/json",
                                "Authorization", "Bearer " + token).when()
                        .get("/users");
        jp = JsonPath.from(res.asString()); // Converting Response to json path obj
        List<String> jsonResponse = jp.get("data.");
        Assert.assertTrue(jsonResponse.size() >= arr.length);
        System.out.println(res.getHeaders()); // printing all headers
        System.out.println(res.statusLine()); // printing status line of response body
    }

    @Test(dataProvider = "dp", description = "test using POJO class")
    public void PostTest(final String firstName, final String lastName, final String gender, final String email, final String status) throws JsonProcessingException {
        apiPojo p = new apiPojo(firstName, lastName, gender, email, status);
        Response r = given().headers("Accept", "application/json", "Content-Type", "application/json",
                "Authorization", "Bearer " + token)
                .body(p)
                .when().post("/users");
        System.out.println(r.statusCode());
//        Code to convert pojo to json response
//        ObjectMapper om = new ObjectMapper();
//        String pload = om.writerWithDefaultPrettyPrinter().writeValueAsString(p);
//        System.out.println(pload);
    }

    @Test
    public void PutTest() {
        Response r = given().body(new HashMap<String, Object>() {
            {
                put("name", "Ankur");
                put("job", "Engineer");
            }
        }).when().put("https://reqres.in/api/users/2");
        System.out.println(r.asString());
    }

    @Test
    public void currencyConverterApiTest() {
        Response r = given().headers("x-rapidapi-host", "currency-exchange.p.rapidapi.com", "x-rapidapi-key",
                "7937d89c90msh7abf240c7c6f8e6p15c0fajsn1cbc5b4d0048").when().get("https://currency-exchange.p.rapidapi.com/listquotes");
        System.out.println(r.asString());
    }

    @Test
    public void mortgagePaymentsTest() {
        Response r = given().headers(new HashMap<String, Object>() {
            {
                put("x-rapidapi-host", "shaisachs-mortgage-payments-v1.p.rapidapi.com");
                put("x-rapidapi-key", "7937d89c90msh7abf240c7c6f8e6p15c0fajsn1cbc5b4d0048");
                put("useQueryString", "true");
            }
        }).and().queryParams(new HashMap<String, Object>() {
            {
                put("price", "10000");
                put("downPayment", "200");
                put("interestRate", "8");
            }
        }).when().get("https://shaisachs-mortgage-payments-v1.p.rapidapi.com/payments");
        System.out.println(r.asString());
    }

    @Test
    public void patchRequestTest() {
        Response r = given()
                .baseUri("https://reqres.in/api/users/2")
                .contentType(ContentType.JSON)
                .body(jsonString)
                .when().patch();
        System.out.println(r.asString());
        Assert.assertEquals(r.statusCode(), 200);
    }

}
