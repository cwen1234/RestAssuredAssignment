import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetCurrentPriceTest {

    @BeforeClass
    public void setUp() {
        baseURI = "https://api.coindesk.com";
    }

    @Test
    public void checkBPIs(){

        // 2a. Verify the response contains 3 BPIs (USD, GBP, EUR)
       Response response = given()
                            .when()
                            .get("/v1/bpi/currentprice.json")
                            .then()
                                .extract().response();
                            String usd = response.jsonPath().getString("bpi.USD");
                            Assert.assertNotNull(usd);
                            String gbp = response.jsonPath().getString("bpi.GBP");
                            Assert.assertNotNull(gbp);
                            String eur = response.jsonPath().getString("bpi.EUR");
                            Assert.assertNotNull(eur);

    }

    @Test
    public void checkGBPDescription(){

        // 2b. Verify the response contains - The GBP ‘description’ equals ‘British Pound Sterling’.
        given()
                .when()
                .get("/v1/bpi/currentprice.json")
                .then()
                .statusCode(200)
                .body("bpi.GBP.description", equalTo("British Pound Sterling"));

    }
}
