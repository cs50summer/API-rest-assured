package trainingxyz;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class Apitests {

    private static final Logger log = LoggerFactory.getLogger(Apitests.class);

    @Test
    public void getCategories(){
        String endpoint ="http://localhost:80/api_testing/category/read.php";
        io.restassured.response.ValidatableResponse response =given().when().get(endpoint).then();
        // ValidatableResponse body = response.log().body();
        response.log().body();
    }

    @Test
    public void getProduct(){
        String endpoint ="http://localhost:80/api_testing/product/read_one.php";
        io.restassured.response.ValidatableResponse response=
                 given().
                        queryParam("id", 2).
                        when().
                        get(endpoint).then();
        response.log().body();
    }

    @Test
    public void createProduct(){
        String endpoint ="http://localhost:80/api_testing/product/create.php";
        String body = """
                { "name":"Water Bottle",
                  "description":"Blue in color . Holds 64oz of water"
                  "price": 15
                  "category_id":3
                }""" ;
        var response = given().body(body).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void updateProduct(){
        String endpoint ="http://localhost:80/api_testing/product/update.php";
        String body= """
                "id":3
                "name":"Water Bottle"
                "description":"Blue in color . Holds 64oz of water"
                "price": 25,
                "category_id":3
                """;
        var response = given().body(body).when().put(endpoint).then();
        response.log().body();
    }
}
