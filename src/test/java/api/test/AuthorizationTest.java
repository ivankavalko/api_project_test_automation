package api.test;

import api.test.entity.CreateTokenResponse;
import api.test.entity.ErrorResponse;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


public class AuthorizationTest {


    @Test(dataProvider = "inputCorrectValues")
    public void authSuccessTest(String username, String password) {
        //Arrange

        String addURI = "https://restful-booker.herokuapp.com/auth";

        String payload = "{\n" +
                "    \"username\" : \"" + username + "\",\n" +
                "    \"password\" : \"" + password + "\"\n" +
                "}";
        
        HttpHeaders headers = new HttpHeaders();

        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");


        HttpEntity<String> entity = new HttpEntity<>(payload, headers);


        //Act

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CreateTokenResponse> response = restTemplate.exchange(addURI, HttpMethod.POST, entity, CreateTokenResponse.class);


        //Assert
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        CreateTokenResponse responseBody = response.getBody();
        assertNotNull(responseBody);
        assertNotNull(responseBody.getToken());
    }

    @Test(dataProvider = "inputValuesInvalid")
    public void authFailTest(String username, String password) {
        //Arrange
        String addURI = "https://restful-booker.herokuapp.com/auth";

        String payload = "{\n" +
                "    \"username\" : \"" + username + "\",\n" +
                "    \"password\" : \"" + password + "\"\n" +
                "}";
        HttpHeaders headers = new HttpHeaders();

        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");


        HttpEntity<String> entity = new HttpEntity<>(payload, headers);

        //Act

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ErrorResponse> response = restTemplate.exchange(addURI, HttpMethod.POST, entity, ErrorResponse.class);

        //Assert
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        ErrorResponse responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(responseBody.getReason(), "Bad credentials");

    }


    @DataProvider(name = "inputValuesInvalid")
    public Object[][] valuesUsernameAndPassword() {
        return new Object[][]{
                {"qwerty", "2"},
                {"test", "test1"},
                {"", "0"}
        };
    }

    @DataProvider(name = "inputCorrectValues")
    public Object[][] valuesCorrectUsernameAndPassword(){
        return new Object[][] {
                {"admin", "password123"}
        };
    }

}
