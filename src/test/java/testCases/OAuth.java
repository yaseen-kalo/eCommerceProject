package testCases;

import Pojo.OAuth.LoginRequest;
import Pojo.OAuth.LoginResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import Common.BaseClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class OAuth extends BaseClass {

    static String token;
    static String userId;

    @Test
    public void getAuthData() {
        RequestSpecification request = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON).build();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail(getUserEmail());
        loginRequest.setUserPassword(getUserPassword());

        RequestSpecification reqLogin =  given().spec(request).body(loginRequest);

        LoginResponse loginResponse= reqLogin.when().post("/api/ecom/auth/login")
                .then().extract().response().as(LoginResponse.class);

        System.out.println("\n");
        token = loginResponse.getToken();
        loginResponse.setToken(token);
        //System.out.println("Token: " + token);
        userId = loginResponse.getUserId();
        loginResponse.setUserId(userId);
        //System.out.println("User ID: " + userId);
        logger.info("Authenticaltion token and UserId has been generated successfully");
    }


    public static String sendToken() {
        return token;
    }

    public static String sendUserId() {
        return userId;
    }

}
