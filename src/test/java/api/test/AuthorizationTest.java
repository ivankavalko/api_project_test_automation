package api.test;

import api.test.entity.CreateTokenResponse;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


public class AuthorizationTest {

    @Test
     public void authToken(){
        //Arrange

        String addURI = "https://restful-booker.herokuapp.com/auth";

        String payload = """
                {
                    "username" : "admin",
                    "password" : "password123"
                }
                """;
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
}
