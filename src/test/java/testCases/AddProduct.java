package testCases;

import Common.BaseClass;
import Pojo.AddProductPojo.ProductResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class AddProduct extends BaseClass {

    static String productId;
    @Test
    public void addProduct() {

        String token = OAuth.sendToken();
        String userId =OAuth.sendUserId();

        if(token != null && userId != null) {

            logger.info("Authenticaltion token and User has been fetched successfully");

            RequestSpecification request = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                    .addHeader("Authorization", token).build();

            RequestSpecification reqProduct = given().spec(request)
                    .param("productName", "Hair Trimmer")
                    .param("productAddedBy", userId)
                    .param("productCategory", "Electronics")
                    .param("productSubCategory", "Human Electronics")
                    .param("productPrice", "100")
                    .param("productDescription", "This is a hair trimmer")
                    .param("productFor", "unisex")
                    .multiPart("productImage", new File("//Users//Muhammed//Desktop//abc.jpeg"));

            ProductResponse createProduct = reqProduct.when().post("/api/ecom/product/add-product")
                    .then().extract().response().
                    as(ProductResponse.class);

            System.out.println("\n");
            System.out.println("Product ID: " + createProduct.getProductId());
            productId = createProduct.getProductId();
            logger.info("Product has been Added successfully");


        } else {
            throw new RuntimeException("Token or User ID is null. Please check the login process.");
        }
    }

    public static String sendProductId() {
        return productId;
    }


}
