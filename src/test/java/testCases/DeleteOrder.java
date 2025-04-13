package testCases;

import Common.BaseClass;
import Pojo.DeleteOrder.ResponseOrder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteOrder extends BaseClass {

    @Test
    public void deleteOrder() {

        String orderId = OrderConfirmation.orderId;
        String token = OAuth.sendToken();

        if(token == null || orderId == null) {
            throw new RuntimeException("Token or Order ID is null. Please check the login process.");
        }
        else {
            logger.info("Authenticaltion token and OrderId has been fetched successfully");
            RequestSpecification request = new RequestSpecBuilder()
                    .setBaseUri("https://rahulshettyacademy.com")
                    .addHeader("Authorization", token)
                    .addPathParam("orderId", orderId)
                    .build();

            RequestSpecification deleteOrder = given()
                    .spec(request);

            ResponseOrder responseOrder =  deleteOrder.when()
                    .delete("/api/ecom/order/delete-order/{orderId}")
                    .then().extract().response().as(ResponseOrder.class);

            System.out.println(responseOrder.getMessage());
            logger.info("Order has been deleted successfully");
        }
    }
}
