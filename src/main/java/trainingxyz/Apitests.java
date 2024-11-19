package trainingxyz;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import models.Product;
import org.hamcrest.Matchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
//import static sun.jvm.hotspot.utilities.AddressOps.greaterThan;

public class Apitests {

    private static final Logger log = LoggerFactory.getLogger(Apitests.class);

    @Test
    public void getCategories(){
        String endpoint ="http://localhost:80/api_testing/category/read.php";
        io.restassured.response.ValidatableResponse response =given().when().get(endpoint).then();
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
                  "description":"Blue in color . Holds 64oz of water",
                  "price": 15,
                  "category_id":3,
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
                "category_id":19
                """;
        var response = given().body(body).when().put(endpoint).then();
        response.log().body();
    }

    @Test
    public void deleteProduct(){
        String endpoint ="http://localhost:80/api_testing/product/delete.php";
        String body= """
                "id":1000
                """;
        var response = given().body(body).when().delete(endpoint).then();
        response.log().body();
    }

    @Test
    public void createSerializedProduct(){
        String endpoint ="http://localhost:80/api_testing/product/create.php";
        Product product= new Product(
                   19 ,
                "Water Bottle",
                "Blue , 64 oz",
                12,
                3
                );
        var response = given().body(product).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void createSweatband(){
        String endpoint ="http://localhost:80/api_testing/product/create.php";
        String body = """
                { "id": "45",
                  "name": "Sweatband",
                  "description": "Red , Elastic material . One size fits all ",
                  "price": "5" ,
                  "category_id": 3,
                  "category_name": "Sweatband",
                }""" ;
        var response= given().body(body).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void updateSweatband(){
        String endpoint ="http://localhost:80/api_testing/product/update.php";
        String body= """
                {
                "id":45
                "name":"Sweatband",
                "description":"Red , Elastic material . One size fits all ",
                "price": 10,
                "category_id":3,
                "category_name":"Sweatband"}
                """;
        var response = given().body(body).when().put(endpoint).then();
        response.log().body();
    }

    @Test
    public void getSweatband(){
        String endpoint ="http://localhost:80/api_testing/product/read_one.php";
        var response = given().
                        queryParam("id", 3).when().get(endpoint).then();
        response.log().body();
    }

    @Test
    public void deleteSweatband(){
        String endpoint ="http://localhost:80/api_testing/product/delete.php";
        String body= """
                "id":"45"
                """;
        var response = given().body(body).when().delete(endpoint).then();
        response.log().body();
    }

    @Test
    public void responseChecks(){
        String endpoint ="http://localhost:80/api_testing/product/read_one.php";

         given().
                queryParam("id", 2).
                when().
                get(endpoint).then().assertThat().
                 statusCode(200).
                 body("id", equalTo("2")).
                 body("name",equalTo("Cross-Back Training Tank"));

    }

    @Test
    public void responseBodyValidations(){
        String endpoint ="http://localhost:80/api_testing/product/read.php";

        given().when().
                get(endpoint).
                then().
                log().
                body().
                assertThat().
                statusCode(200).
                body("records.size()", greaterThanOrEqualTo(0)).
                body("records.id", everyItem(notNullValue())).
                body("records.name",everyItem(notNullValue())).
                body("records.description",everyItem(notNullValue())).
                body("records.price",everyItem(notNullValue())).
                body("records.category_id",everyItem(notNullValue())).
                body("records.category_name",everyItem(notNullValue())).body("records.id[0]",equalTo(1002));


    }
}
