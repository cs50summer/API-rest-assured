package trainingxyz;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
// import static io.restassured.RestAssured.when;
// import static jdk.internal.org.jline.utils.Colors.s;


/*import static sun.net.InetAddressCachePolicy.get;*/

public class Apitests {

    private static final Logger log = LoggerFactory.getLogger(Apitests.class);

    @Test
    public void getCategories(){
        String endpoint ="http://localhost:80/api_testing/category/read.php";
        io.restassured.response.ValidatableResponse response =given().when().get(endpoint).then();
        // ValidatableResponse body = response.log().body();
        response.log().body();

        //var response: ValidatableResponse = given().when().get(endpoint).then();
        /*:ValidatableResponse = given().when().get(endpoint).then(); */
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



}
