package testCases;

import Common.BaseClass;
import Pojo.DeleteProduct.ResponseProduct;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteProduct extends BaseClass {

    @Test
    public void DeleteProduct() {
        String token = OAuth.sendToken();
        String productId = AddProduct.sendProductId();

        if (token == null || productId == null) {
            throw new RuntimeException("Token or Product ID is null. Please check the login process.");
        } else {
            logger.info("Authenticaltion token and ProductId has been fetched successfully");
            RequestSpecification request = new RequestSpecBuilder()
                    .setBaseUri("https://rahulshettyacademy.com")
                    .addHeader("Authorization", token)
                    .addPathParam("productId", productId)
                    .build();

            RequestSpecification deleteProduct = given()
                    .spec(request);

            ResponseProduct responseProduct = deleteProduct.when()
                    .delete("/api/ecom/product/delete-product/{productId}")
                    .then().extract().response().as(ResponseProduct.class);

            System.out.println(responseProduct.getMessage());

            logger.info("Product has been deleted Successfully");
        }
    }
}
