package testCases;

import Common.BaseClass;
import Pojo.OrdersPojo.Order;
import Pojo.OrdersPojo.RequestRoot;
import Pojo.OrdersPojo.ResponseRoot;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@Test
public class OrderConfirmation extends BaseClass {

    static String orderId;
    public void OrderConfirmation() {

        String token = OAuth.sendToken();
        String productId = AddProduct.sendProductId();

        if(token == null || productId == null) {
            throw new RuntimeException("Token or Product ID is null. Please check the login process.");
        }
        else {
            logger.info("Authenticaltion token and ProductId has been fetched successfully");
            System.out.println("Token from Order Confirmation Class: " + token);
            System.out.println("Product ID from Order Confirmation Class: " + productId);

            RequestSpecification request = new RequestSpecBuilder()
                    .setBaseUri("https://rahulshettyacademy.com")
                    .addHeader("Authorization", token)
                    .setContentType("application/json")
                    .build();

            Order order = new Order();
            order.setCountry("India");
            order.setProductOrderedId(productId);

            List<Order> orderList = new ArrayList<>();
            orderList.add(order);

            RequestRoot requestRoot = new RequestRoot();
            requestRoot.setOrders(orderList);

            RequestSpecification reqOrderConfirmation = given()
                    .spec(request).body(requestRoot);

            ResponseRoot responseRoot = reqOrderConfirmation.when()
                    .post("/api/ecom/order/create-order")
                    .then()
                    .extract().response().as(ResponseRoot.class);

            orderId = responseRoot.getOrders().get(0);
            logger.info("Order has been placed successfully");
            //System.out.println("Response: " + responseRoot.getOrders().get(0));
//            responseRoot.getOrders()
//                    .stream()
//                    .forEach(orderId -> System.out.println("Order ID: " + orderId));
            }
        }

        public static String getOrderId() {
            return orderId;
        }

    }